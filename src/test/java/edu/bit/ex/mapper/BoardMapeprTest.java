package edu.bit.ex.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import edu.bit.ex.board.mapper.BoardMapper;
import edu.bit.ex.board.vo.BoardVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

//기본적으로 세 개 필요하다.
@RunWith(SpringRunner.class) 
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapeprTest {
	
	@Setter(onMethod_= @Autowired)
	private BoardMapper boardMapper;
	
	@Test
	public void testBoardMapper() {
		assertNotNull(boardMapper);
	}
	
	@Test
	public void testGetList() {
		List<BoardVO> list = boardMapper.getList();
		log.info(boardMapper);
		
		for(BoardVO boardVO : list) {
			log.info(boardVO.getbContent());
		}
	}
	
	//delete
	@Test
	public void testDelete() {
		boardMapper.delete(1000);
		log.info(boardMapper);
	}
}
