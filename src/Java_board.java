import java.util.Scanner;

public class Java_board {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String title = "";
		String body = "";
		while (true) {
			System.out.print("명령어 입력: ");
			String str = sc.next();

			if (str.equals("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			if (str.equals("add")) {
				System.out.println("게시물 제목 입력 : ");
				title = sc.next();
				System.out.println("게시물 내용 입력 : ");
				body = sc.next();
				System.out.println("게시물이 등록되었습니다.");
			}
			if (str.equals("list")) {
				System.out.println("제목 : " + title);
				System.out.println("내용 : " + body);
			}
		}

	}

}
