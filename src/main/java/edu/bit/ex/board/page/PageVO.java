package edu.bit.ex.board.page;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageVO {
	// 페이징 처리 할 때 필요한 정보들

	// private int displayPageCount = 10; // 이렇게도 가능하다
	private int startPage; // 화면에 보여지는 시작 번호
	private int endPage; // 화면에 보여지는 끝 번호
	private boolean prev, next; // 이전과 다음으로 이동 가능한 링크 표시

	private int total; // 전체 글 개수
	private Criteria cri; // criteria 가져옴

	public PageVO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total; // 전체 데이터 수

		// Total을 통한 endPage의 재계산
		// 예1) 10개씩 보여주는 경우 전체 데이터 수가 80개라고 가정하면 끝번호는 10이 아닌 8이 됨
		// 예2) 현재 페이지가 13이면 13/10 = 1.3 올림 ->2 끝페이지는 2*10=20
		// Math.ceil : 올림 // 10은 스태틱 변수로 선언해서 사용해도 된다. 여기선 10으로 그냥 지정함
		// (페이지번호 /10)을 올림한 값에 10을 곱해준다.
		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0) * 10);
		// this.endPage = (int) (Math.ceil(cri.getPageNum() / displayPageCount) *
		// displayPageCount);

		// 시작 페이지는 무조건 마지막 페이지 -9 -> (endPage - (amount-1))
		this.startPage = this.endPage - 9;

		// 실제 페이지가 작으면 8을 end페이지에 들어가게 해줌 (전체 게시글 수 / 페이지당 글의 갯수)
		int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));

		// 만약에 진짜 마지막 페이지가 endPage보다 작으면
		// 끝번호는 마지막 페이지가 되어야 하므로 그게 endPage가 되도록 설정
		if (realEnd < this.endPage) {
			this.endPage = realEnd;
		}

		// startPage(시작번호)가 1보다 큰 경우 존재함
		this.prev = this.startPage > 1;

		// realEnd가 (끝번호)endPage보다 큰 경우에만 존재함
		this.next = this.endPage < realEnd;
	}

	public String makeQuery(int page) { //클릭을 할 때 마다 서버에서 받아야해서 좀 더 쉽게 넘기기 위해서 겟방식으로 쿼리를 만들어 쉽게 달아주기 위해 만든것. 
		UriComponents uriComponentsBuilder = UriComponentsBuilder.newInstance().queryParam("pageNum", page) // pageNum = 3
				.queryParam("amount", cri.getAmount()) // pageNum=3&amount=10
				.build(); // ?pageNum=3&amount=10
		return uriComponentsBuilder.toUriString(); // ?pageNum=3&amount=10 리턴
	}

}
