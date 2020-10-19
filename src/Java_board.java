import java.util.ArrayList;
import java.util.Scanner;

public class Java_board {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		ArrayList<String> titles = new ArrayList<>();
		ArrayList<String> bodies = new ArrayList<>();

		int size = 0;

		while (true) {
			System.out.print("명령어 입력: ");
			String str = sc.next();

			if (str.equals("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			if (str.equals("add")) {
				System.out.print("게시물 제목 입력 : ");
				titles.add(sc.next());
				System.out.print("게시물 내용 입력 : ");
				bodies.add(sc.next());
				System.out.println("게시물이 등록되었습니다.");
				size++;
			}
			if (str.equals("list")) {
				for (int i = 0; i < size; i++) {
					System.out.println("제목 : " + titles.get(i));
					System.out.println("내용 : " + bodies.get(i));
					System.out.println("======================");

				}
			}
			if (str.equals("update")) {
				System.out.print("수정할 게시물 선택 : ");
				String targetTitle = sc.next();			
				
				for(int i = 0; i < titles.size(); i++) {
					String title = titles.get(i);
					if (titles.equals(targetTitle)) {
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
		}
	}
}
