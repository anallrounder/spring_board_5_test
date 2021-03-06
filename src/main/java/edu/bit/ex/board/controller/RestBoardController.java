package edu.bit.ex.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.bit.ex.board.service.BoardService;
import edu.bit.ex.board.vo.BoardVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

// REST: REpresentational State Transfer
// 하나의 URI가 하나의 고유한 리소스를 대표하도록 설계된 개념 

//http://localhost/spring02/list?bno=1 ==> url+파라미터
//http://localhost/spring02/list/1 ==> url

// RestController를 스프링 4.0부터 지원
// @Controller, @RestController 차이점은 메서드가 종료되면 화면 전환의 유무

@Log4j
@AllArgsConstructor
@ResponseBody
@RestController // 기존하고 다르게 처리하겠다. -> 리턴값 뷰아니고 다른거로 하겠다.
@RequestMapping("/restful/*") // 이걸로 치고 들어오는 모든걸 이 클래스가 해결해라.
public class RestBoardController {

	private BoardService boardService;

	// 1. list(처음 진입 화면이므로 화면이 깜박여도 상관 없으므로 @Controller방식으로 접근 -view(화면)을 리턴
	@GetMapping("/board")
	public ModelAndView list(ModelAndView mav) {
		// 기존방식으로 넘기려면 ModelAndView로 넘기면된다.
		// 제이슨으로 넘길수도있긴하다.(그런데 꼭 제이슨으로 넘겨야만 하는 것은 아님)
		mav.setViewName("rest/rest_list");
		mav.addObject("list", boardService.getList());
		return mav;
	}

	// 2. 컨턴츠뷰는 제목을 눌렀을 때, board/200이렇게 넘어와야한다.
	@GetMapping("/board/{bId}") // 이거 처리하는방법이 1)PathVariable로 처리해도 되고,(아래처럼)
	public ModelAndView rest_content_view(BoardVO boardVO, ModelAndView mav) {// 2)커맨드 객체가 가장 좋다.
		// public String rest_content_view(@PathVariable("bId") String bId, Model model)
		// {
		log.info("rest_content_view");
		mav.setViewName("rest/rest_content_view");
		mav.addObject("content_view", boardService.get(boardVO.getbId()));
		return mav;
	}

	// 3. delete
	@DeleteMapping("/board/{bId}") // 맵핑 자체가 델리트맵핑
	public ResponseEntity<String> rest_deltete(BoardVO boardVO, Model model) {

		ResponseEntity<String> entity = null; // 레스트풀을 위해 제공하는 대표적인 것 중 하나
		log.info("rest_delete");

		try {
			boardService.remove(boardVO.getbId());
			// 삭제가 성공하면 성공 상태 메세지 저장 // 마음대로 전달할 수 있다.
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			// message = "SUCCESS" 이렇게 해도 상관 없다.
		} catch (Exception e) {
			e.printStackTrace();
			// 댓글 삭제가 실패하면 실패 상태메세지 저장
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);// 400
		}
		// 삭제 처리 HTTP 상태 메시지 리턴
		return entity;
	}

	// 4. 수정
	@PutMapping("/board/{bId}")
	public ResponseEntity<String> rest_update(@RequestBody BoardVO boardVO, ModelAndView modelAndView) {

		ResponseEntity<String> entity = null;

		log.info("rest_update..");
		try {
			// boardService.update(boardVO);
			int rn = boardService.update(boardVO);
			log.info("update 넘어온 숫자:::::" + rn);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// write_view
	@GetMapping("/board/rest_write_view")
	public ModelAndView rest_write_view(ModelAndView mav) {
		log.info("rest_write_view");
		mav.setViewName("rest/rest_write_view");
		return mav;
	}

	// CREATE_write_insert
	@PostMapping("/board/rest_write") 
	public ResponseEntity<String> rest_insert(@RequestBody BoardVO boardVO, Model model) {
	
		ResponseEntity<String> entity = null;
		log.info("rest_insert");

		try {
			boardService.writeBoard(boardVO);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// reply_view
	@GetMapping("/board/reply/{bId}")
	public ModelAndView rest_reply_view(BoardVO boardVO, ModelAndView mav) {
		log.info("rest_reply_view");
		mav.setViewName("rest/rest_reply_view");
		mav.addObject("reply_view", boardService.get(boardVO.getbId()));
		return mav;
	}
	
	//reply
	@PostMapping("/board/reply/{bId}") 
	public ResponseEntity<String> rest_reply(@RequestBody BoardVO boardVO, Model model) {
		ResponseEntity<String> entity = null;
		log.info("rest_reply");

		try {
			boardService.replyBoard(boardVO);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}