package edu.bit.ex.database;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.log4j.Log4j;

//스프링에대한 컨테이너만드는 환경을 만들어주는 애노테이션이 존재한다. 
@RunWith(SpringRunner.class) // 컨테이너 만들고
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
// 웹.xml로 원래 톰캣이 읽어들이면서 해야하는데, junit은 그걸 못해서 그 환경을 만들어주는 것이다.
// 루트.xml에서 읽어와서 dataSource 가져와서 마이바티스까지 가져온다. 

@Log4j
public class ConnectionTest {

	@Inject // 이게 커넥션 풀. 커넥션 풀이 핵심이다. 
	//혹은 @Autowired 써도 된다.
	DataSource dataSource;

	@Test
	public void testDataSource() {
		assertNotNull(dataSource); //객체 제대로 들어오고 있다.
	}
}
