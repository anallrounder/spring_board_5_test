package edu.bit.ex.calculator;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CalculatorTest {
	
	@Autowired //Atowired는 객체 주입
	Calculator cal;
	
	@Test
	public void testCalculator() {	
		assertNotNull(cal); 
	}
	
	@Test
	public void testSum() {	//sum함수에 대해서 테스트를 한다는 의미.
		Calculator cal = new Calculator();
		int result = cal.sum(10, 20);
		assertEquals(20,result,10); // 이런 함수가 몇 개 있다.
		// 이 함수는 result값하고  앞이 정답 뒤가 차이나는거. 차이
		// assertEquals(50,result,10); 결과인 30과 50이 10 이상 차이나면 문제있다고 나옴
		System.out.println(result);
	}
	
	@Test
	public void testSub() {	
		Calculator cal = new Calculator();
		int result = cal.sub(10, 20);
		assertEquals(-10,result);
		System.out.println(result);
	}
	
	//이런식으로 개발하는 것이 좋다.
	// 예전에는 폭포수모델이라고 전체 프로젝트 끝나고 확인했는데 이제는 작은것 하나씩 확인해나간다.
	//https://wooaoe.tistory.com/33 
	// TDD의 대표적은 아니고 단위테스트의 대표적인 툴이 JUnit
}
