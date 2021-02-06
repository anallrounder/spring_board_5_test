package edu.bit.ex.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.bit.ex.board.service.BoardService;
import edu.bit.ex.board.vo.BoardVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
  
//우리는 5쓰고있다?
//spring  v4.0 이전 스프링에서의 JSON (@Controller + @ResponseBody) 
@Log4j
@AllArgsConstructor
@Controller
public class RestBoardSpring4BeforeController {

  private BoardService boardService;

  @ResponseBody // 새로 달아줌 ->기존에 했던 문법사항과 완전히 달라짐
  @GetMapping("/rest/before")
  public List<BoardVO> before(Model model) throws Exception { 
    //예전엔 리턴타입을 view를 적어줬다. 
    //responseBody를 붙이면서 지금까지 문법사항과 달라지는 것!
    
    log.info("before");
    //model.addAttribute("list",  boardService.getList());
    List<BoardVO> list = boardService.getList();
    
    return boardService.getList();
  }

}