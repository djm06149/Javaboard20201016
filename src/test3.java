import java.util.Scanner;

public class test3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int currentPageNo = sc.nextInt();
		
		int totalCntOfItems = 20; //전체게시물 개수
		int startPageNo = 1; //시작 페이지 번호
		int itemsCntOfPerPage = 3; // 페이지당 출력 게시물 개수
		int pageCntPerBlock = 5;// 한 페이지 블럭 당 페이지 개수
		int endPageNo = (int)Math.ceil((double)totalCntOfItems / itemsCntOfPerPage); // 마지막 페이지 번호
		int currentPageBlock = currentPageNo / pageCntPerBlock; // 현재 페이지 블록
		
		int startPageNoInBlock = (currentPageBlock - 1) * pageCntPerBlock;

//		for (int i = startPageNoInBlock; i <= endPageNoInBlock; i++) {
//			if (i == currentPageNo) {
//				System.out.print("[" + i + "]");
//			} else {
//				System.out.print(i + " ");
//			}
//		}

	}


}