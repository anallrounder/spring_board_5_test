package edu.bit.ex.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.bit.ex.board.mapper.BoardMapper;
import edu.bit.ex.board.page.Criteria;
import edu.bit.ex.board.vo.BoardVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@AllArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
	
	private BoardMapper mapper;
	
	@Override
	public List<BoardVO> getList() {
		log.info("get all Lists completed");
		return mapper.getList();
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("get List with criteria: " + cri);
		return mapper.getListWithPaging(cri);
	}
	
	@Override
	public int getTotal(Criteria cri) {
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}
		
//	@Override
//	public List<BoardVO> getTables() {
//		log.info("get all Lists completed");
//		return mapper.getTables();
//	}


	@Override
	public void writeBoard(BoardVO boardVO) {
		log.info("inserted-----------");
		mapper.insert(boardVO);	
	}

	@Override
	public BoardVO getBoard(int bId) {
		log.info("getBoard--------------");
		return mapper.read(bId);	
	}

	@Override
	public void modifyBoard(BoardVO boardVO) {
		log.info("modified-----------");
		mapper.modify(boardVO);
	}

	@Override
	public void deleteBoard(int bId) {
		log.info("Board deleted-----------");
		mapper.delete(bId);	
	}

	@Override
	public BoardVO getReplyBoard(int bId) {
		log.info("getReplyBoard-----------");
		return mapper.readyToReply(bId);	
	}

	@Override
	public void replyBoard(BoardVO boardVO) {
		log.info("replied-----------");
		//mapper.reply(boardVO);
		mapper.updateShape(boardVO);
		mapper.insertReply(boardVO);
	}

	@Override
	public void upHit(BoardVO boardVO) {
		log.info("upHit-----------");
		mapper.hitUpdate(boardVO);
	}

	/*
	RESTful
	*/
	
	@Override
	public BoardVO get(int bId) {
		log.info("get-----------");
		return mapper.read(bId);
	}

	@Override
	public void remove(int bId) {
		log.info("deleted-----------");
		mapper.delete(bId);	
	}

	@Override
	public int update(BoardVO boardVO) {
		log.info("modified-----------");
		
		return mapper.modify(boardVO);
	}

	@Override
	public void insert(BoardVO boardVO) {
		log.info("inserted-----------");
		mapper.insert(boardVO);	
	}

}
