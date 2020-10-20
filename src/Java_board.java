import java.util.ArrayList;
import java.util.Scanner;

public class Java_board {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		ArrayList<Integer> ids = new ArrayList<>();
		ArrayList<String> titles = new ArrayList<>();
		ArrayList<String> bodies = new ArrayList<>();
		ArrayList<Article> articles = new ArrayList<>();

		int no = 1;

		while (true) {
			System.out.print("명령어 입력: ");
			String str = sc.next();

			if (str.equals("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			if (str.equals("add")) {
				
				Article a = new Article();
				
				a.id = no;
				
				ids.add(no);
				no++;
				System.out.print("게시물 제목 입력 : ");
//				a.title = title;
				titles.add(sc.next());
				System.out.print("게시물 내용 입력 : ");
//				a.body = body;
				bodies.add(sc.next());
				System.out.println("게시물이 등록되었습니다.");

			}
			if (str.equals("list")) {
				for (int i = 0; i < ids.size(); i++) {
					System.out.println("번호 : " + ids.get(i));
					System.out.println("제목 : " + titles.get(i));
					System.out.println("내용 : " + bodies.get(i));
					System.out.println("======================");

				}
			}
			if (str.equals("update")) {
				System.out.print("수정할 게시물 선택 : ");
				int targetId = sc.nextInt();

				for (int i = 0; i < ids.size(); i++) {
					int id = ids.get(i);
					if (id == targetId) {
						System.out.print("게시물 제목 입력 : ");
						String newTitle = sc.next();
						System.out.print("게시물 내용 입력 : ");
						String newBody = sc.next();

						titles.set(i, newTitle);
						bodies.set(i, newBody);
						break;

					}
				}
			}
			if (str.equals("delete")) {
				System.out.print("몇번 게시물을 지우시겠습니까 : ");
				int targetId = sc.nextInt();
				int existFlag = 2; // 1. 있음 2. 없음

				for (int i = 0; i < ids.size(); i++) {
					int id = ids.get(i);
					if (id == targetId) {
						existFlag = 1;
						ids.remove(i);
						titles.remove(i);
						bodies.remove(i);
						break;
					}
				}

				if (existFlag == 2) {

					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					
					System.out.println(targetId + "번 게시물이 삭제되었습니다.");
				}
			}
		}
	}
}
