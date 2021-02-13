package edu.bit.ex.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import edu.bit.ex.board.vo.UserVO;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */

@Log4j // 여기 컨트롤러 이런거 달면 안된다.
public class BoardInterceptor extends HandlerInterceptorAdapter { 
	// 상속하면 두개 함수를 상속받을 수 있다. 프리핸들러, 포스트 핸들러
	// 컨트롤러 가기전에 중간에서 리퀘스트, 리스펀스 객체를 중간에서 가로챌 수 있는 클래스를 제공하는 것
	// 이 인터셉터 객체는 로드존슨이 만들어서 제공하는것
	// 리퀘스트 객체 안에 세션부터 다 있다.

	
	//내부적으로 프리핸들러, 포스트 핸들러 언제실행? 둘다 스프링이 실행시켜 주는 것
	// 어떻게 객체 생성?? 아무튼 객체만 생성하게되면 프리,포스트는 누가 호출하냐? 스프링이 호출한다.
	// 포스트는 컨트롤러 지나서 돌아올때 거친다. 할거 없다고 부모한테 다 넘겨준다.
	
	//인터셉터는 객체생성하는 방법이 정해져있다. 어디로 가냐면 -> 서블릿 엑셈엘로 간다. 여기서 설정
	
	// preHandle() : 컨트롤러보다 먼저 수행되는 메서드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) // 함수가 세 개 넘어온다.
			throws Exception {
		System.out.println("preHandle 실행");
		// session 객체를 가져옴
		HttpSession session = request.getSession();

		// login처리를 담당하는 사용자 정보를 담고 있는 객체를 가져옴
		UserVO user = (UserVO) session.getAttribute("user");

		if (user == null) {
			log.info("user가 null");
			// 로그인이 안되어 있는 상태이므로 로그인 폼으로 다시 돌려보냄(redirect)
			response.sendRedirect(request.getContextPath()); // getContextPath -> /ex 를 말함 

			return false; // 더이상 컨트롤러 요청으로 가지 않도록 false로 반환함 (더이상 진행하지 못하게 만든다.)
		}

		// preHandle의 return은 컨트롤러 요청 uri로 가도 되냐 안되냐를 허가하는 의미임
		// 따라서 true로하면 컨트롤러 uri로 가게 됨.
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		super.postHandle(request, response, handler, modelAndView);
		System.out.println("postHandle 실행");
	}
}
