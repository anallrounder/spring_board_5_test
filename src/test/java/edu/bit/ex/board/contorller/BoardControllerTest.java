package edu.bit.ex.board.contorller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@Log4j
public class BoardControllerTest {

	@Setter(onMethod_ = { @Autowired }) 
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	@Before // 골뱅이 테스트 하기전에 미리 실행하는것을 의미한다. = 테스트 초기화
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build(); //이렇게 객체 생성한다. 
	}
	
	@Test // 컨트롤러에서 하는걸 흉내내는것
	public void testList() throws Exception {
		mockMvc.perform(get("/board/list"))	//겟방식으로 리스트를   받는다.
		.andExpect(status().isOk())	//응답검증 200ok 서버
		.andDo(print())	//print 뿌리는거
		.andExpect(forwardedUrl("/WEB-INF/views/board/list.jsp")); //포워딩해서 가상환경에서 돌리는거. 
		// 콘솔에 넘어오는걸 보고 확인해야한다.
	}
	
	@Test
	public void testContent_view() throws Exception {
		mockMvc.perform(get("/board/content_view"))
		.andExpect(status().isOk())	
		.andDo(print())
		.andExpect(forwardedUrl("/WEB-INF/views/board/content_view.jsp"));
	}
}
