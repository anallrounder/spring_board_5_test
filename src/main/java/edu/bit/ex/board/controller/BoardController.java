package edu.bit.ex.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import edu.bit.ex.board.page.Criteria;
import edu.bit.ex.board.page.PageVO;
import edu.bit.ex.board.service.BoardService;
import edu.bit.ex.board.vo.BoardVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
  
@Log4j
@AllArgsConstructor
@Controller
public class BoardController {

	private BoardService boardService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
//	@GetMapping("/board/tables")
//	public String tables(Model model) throws Exception {
//		log.info("tables");
//		model.addAttribute("tables", boardService.getTables() );
//		return"/board/tables";
//	}

//	@GetMapping("/board/index")
//	public String index(Model model) throws Exception {
//		log.info("index");
//		model.addAttribute("index", boardService.getList() );
//		return"/board/index";
//	}

	@GetMapping("/board/list")
	public void list(Model model)throws Exception {
		log.info("list()");
		model.addAttribute("list",  boardService.getList());
	}
	
	//페이징 처리 리스트
	@GetMapping("/board/list2")
	public void list2(Criteria cri, Model model) throws Exception {	
		log.info("list2 호출");
		log.info(cri);
		System.out.println(cri);
		
		model.addAttribute("list", boardService.getList(cri)); //이 부분이 핵심부분이다.
		
		int total = boardService.getTotal(cri);
		log.info("total"+total);
		model.addAttribute("pageMaker", new PageVO(cri, total)); //밑에 뿌리는건 얘가 결정함
		//처음에 최신에서 10개를 가져와야함 
		//total로 전체를 받아온다음 객체를 생성해서 pageMaker로 넘김 (리스트로)
	}

	@GetMapping("/board/write_view")
	public String write_view(Model model) throws Exception {
		log.info("/board/write_view");
		return "/board/write_view";
	}

	@PostMapping("/board/write")
	public String write(BoardVO boardVO, Model model) throws Exception {
		log.info("write");
		boardService.writeBoard(boardVO);
		return "redirect:list";
	}

	@GetMapping("/board/content_view")
	public String content_view(BoardVO boardVO, Model model) throws Exception {
		log.info("content_view");
		boardService.upHit(boardVO);
		model.addAttribute("content_view", boardService.getBoard(boardVO.getbId()));
		return "/board/content_view";
	}

	@PostMapping("/board/modify")
	public String modify(BoardVO boardVO, Model model) throws Exception {
		log.info("modify");
		boardService.modifyBoard(boardVO);
		return "redirect:list";
	}

	@GetMapping("/board/delete")
	public String delete(BoardVO boardVO, Model model) throws Exception {
		log.info("delete");
		boardService.deleteBoard(boardVO.getbId());
		return "redirect:/board/list";
	}

	@GetMapping("/board/reply_view")
	public String reply_view(BoardVO boardVO, Model model) throws Exception {
		log.info("reply_view");
		model.addAttribute("reply_view", boardService.getReplyBoard(boardVO.getbId()));
		return "/board/reply_view";
	}

	@PostMapping("/board/reply")
	public String reply(BoardVO boardVO, Model model) throws Exception {
		log.info("reply");
		boardService.replyBoard(boardVO);
		return "redirect:list";
	}

}
