import java.util.Scanner;

public class Java_board {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String title = "";
		String body = "";
		while (true) {
			System.out.print("��ɾ� �Է� : ");
			String str = sc.next();

			if (str.equals("exit")) {
				System.out.println("���α׷��� �����մϴ�.");
				break;
			}
			if (str.equals("add")) {
				System.out.println("�Խù� ������ ������ּ��� : ");
				title = sc.next();
				System.out.println("�Խù� ������ ������ּ��� : ");
				body = sc.next();
				System.out.println("�Խù��� ��ϵǾ����ϴ�.");
			}
			if (str.equals("list")) {
				System.out.println("���� : " + title);
				System.out.println("���� : " + body);
			}
		}

	}

}
