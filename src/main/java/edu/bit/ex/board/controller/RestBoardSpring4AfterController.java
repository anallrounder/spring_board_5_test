package edu.bit.ex.board.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.bit.ex.board.service.BoardService;
import edu.bit.ex.board.vo.BoardVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */

// spring v4.0에서 부터 @RestController라는 어노테이션을 추가해서 
// 해당 Controller의 모든 메서드의 리턴타입을 기존과 르게 처리한다는 것을 명시
@Log4j
@AllArgsConstructor
@RestController
public class RestBoardSpring4AfterController {

	private BoardService boardService;
	
	//@ResponseBody // 이거를 더이상 안적어줘도  된다.
	@GetMapping("/rest/after")
	public List<BoardVO> after(Model model) throws Exception { 
		log.info("/rest/after");
		List<BoardVO> list = boardService.getList();
		
		return boardService.getList();
	}
	//기존에는 view이용했지만 //@RestController 사용하면서 엑셈엘로, 제이슨으로 리턴하겠다. 
}
