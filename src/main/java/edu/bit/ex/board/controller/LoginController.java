package edu.bit.ex.board.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.bit.ex.board.service.LoginService;
import edu.bit.ex.board.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import edu.bit.ex.board.service.KakaoLoginService;

/**
 * Handles requests for the application home page.
 */
  
@Log4j
@AllArgsConstructor
@Controller
public class LoginController {

	private LoginService loginService;
	
	@GetMapping("/")
	public String home() {
		log.info("home...");
		return "kakaologin";
	}

	@Autowired
    private KakaoLoginService kakao;
	
	
	@RequestMapping("/kakaoCallback")
	public String kakaoCallback(@RequestParam("code") String code, HttpSession session) {
		
		log.info("kakao login_카카오 인증 완료!");
		log.info("code: " + code);
		
		String access_Token = kakao.getAccessToken(code);
		log.info("access_token : " + access_Token);
		
		HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
		log.info("login Controller : " + userInfo);
		
		// 클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
	    if (userInfo.get("email") != null) {
	        session.setAttribute("userId", userInfo.get("email"));
	        session.setAttribute("access_Token", access_Token);
	    }
		
	    session.setAttribute("nickname",  userInfo.get("nickname"));
        session.setAttribute("email",  userInfo.get("email"));
	    
		return "kakaoCallback";
	}
	
	// 카카오 로그아웃
	@RequestMapping(value="/kakaologout")
	public String kakaologout(HttpSession session) {
	    kakao.kakaoLogout((String)session.getAttribute("access_Token"));
	    session.removeAttribute("access_Token");
	    session.removeAttribute("userId");
	    return "kakaologin";
	}
	
	@PostMapping("/login")
	//public String login(UserVO) {// 이게 제일 좋은 방법 // 아래는 옛날 방법
	public String login(HttpServletRequest req, RedirectAttributes rttr) {
		
		log.info("post login");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		//개념 설명할줄알면 5점 스프링에서 활용 어떻게 하는지 알면 만점!! 
		
		//session 처리를 위한 Session 객체 HttpServletREquset안에 있음 
		//세션은 메모리영역! 각각의 유저의 연결성유지를 위해서 있다. 세션영역에는 세션번호가 저장되어 있다.(아주중요!)
		//이 세션 객체안에 임의값을 넣어서 30분동안 유지할 수 있게 함
		HttpSession session = req.getSession();
		
		UserVO user = loginService.loginUser(id, pw);
		
		if (user == null) { // 세션에서 유저 가져옴
			session.setAttribute("user", null);
			
			/*
				Spring3에서 제공하는 RedirectAttributes를 사용하면 redirect post 구현이 가능하다.
				하지만 일회성이다. 새로고침하면 날아가는 데이터이므로 사용목적에 따라서 사용/불가능 판단을 잘 해야한다.
			*/
			rttr.addFlashAttribute("msg", false); //일회성, 쓰든 안쓰든 상관없다. 새로고침하면 날아간다.
			//f5누르면 사라짐
		} else {
			session.setAttribute("user", user);
		}
		
		return "redirect:/";
	}
	
	// 로그아웃
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) throws Exception {
		log.info("/member/logout");

		session.invalidate(); //메모리에서 날린다.

		return "redirect:/";
	}
	
	

}
