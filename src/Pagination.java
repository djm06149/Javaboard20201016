
public class Pagination {

	private int currentPageNo = 1;
	private int totalCntOfItems; // 전체 게시물 개수
	private int startPageNo = 1; // 시작 페이지 번호
	private int itemsCntPerPage = 3; // 페이지당 출력 게시물 개수
	private int pageCntPerBlock = 5; // 한 페이지 블록 당 페이지 개수
	private int endPageNo = (int) Math.ceil((double) totalCntOfItems / itemsCntPerPage); // 마지막 페이지 번호

	// 해당 페이지의 게시물 목록의 첫 인덱스
	int startIndex = (currentPageNo - 1) * itemsCntPerPage;

	// 해당 페이지의 게시물 목록의 마지막 인덱스
	int endIndex = startIndex + itemsCntPerPage;

	
	public int getCurrentPageBlock() {
		int currentPageBlock = (int) Math.ceil((double) currentPageNo / pageCntPerBlock); // 현재 페이지 블록
		
		return currentPageBlock;
		
	}
}
