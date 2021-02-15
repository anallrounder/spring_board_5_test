package edu.bit.ex.board.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAdvice2 {

	@Before("within(edu.bit.board.service.*)")
	public void printLogging()  {
		System.out.println("공통기능 - 프린트 로그");		
	}
	
}
