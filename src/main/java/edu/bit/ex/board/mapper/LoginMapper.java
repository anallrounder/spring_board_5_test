package edu.bit.ex.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import edu.bit.ex.board.vo.UserVO;

@Mapper
public interface LoginMapper {

	@Select("select * from users where username = #{username} and password = #{password}") 
	public UserVO logInUser(@Param("username") String username,@Param("password") String password);
	// 패스워드를위로넘겨준다. 아래 스트링 패스워드 
	//자바문법을 마이바티스문법에 연결해야한다. 파라미터터 이름을,@Param("password") 위에 #{password}로 넘겨라 
	//변수가 두개 이상일때는 @Param꼭 쓰자.
}
