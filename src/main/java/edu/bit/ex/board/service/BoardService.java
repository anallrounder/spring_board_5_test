package edu.bit.ex.board.service;

import java.util.List;

import edu.bit.ex.board.page.Criteria;
import edu.bit.ex.board.vo.BoardVO;

public interface BoardService {

	public List<BoardVO> getList();
	//public List<BoardVO> getTables();
	
	public List<BoardVO> getList(Criteria cri);// 글 목록 조회
	public int getTotal(Criteria cri); //글 갯수
	
	public void writeBoard(BoardVO boardVO); // 글 등록
	public BoardVO getBoard(int getbId); 	// 글 작성 페이지
	public void modifyBoard(BoardVO boardVO);	//글 수정
	public void deleteBoard(int getbId);	// 글 삭제
	public BoardVO getReplyBoard(int getbId);	// 댓글 페이지
	public void replyBoard(BoardVO boardVO);	// 댓글
	public void upHit(BoardVO boardVO);	// 조회수

	//RESTful
	public BoardVO get(int getbId); 
	public void remove(int getbId); 


	
	

}
