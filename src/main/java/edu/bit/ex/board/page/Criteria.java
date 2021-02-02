package edu.bit.ex.board.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//게시판 페이징 전용 클래스
//Criteria : 사전적 의미로는 검색기준, 분류기준

@Setter
//@Getter
@ToString
public class Criteria {
	// 페이징 처리를 위해서 페이지 번호와 한 페이지 당 몇 개의 데이터를 보여줄 지 결정되어야만 한다.
	private int pageNum; // 페이지 번호
	private int amount; // 한 페이지당 보여줄 게시글의 개수 : 몇 개의 데이터를 보여줄 것인가

	public Criteria() { // 기본 세팅
		this(1, 10);	//1 페이지 10개로 지정 // this함수는 생성자 호출
	}

	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public int getAmount() {
		return amount;
	}
	
}
