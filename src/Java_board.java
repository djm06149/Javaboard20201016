import java.util.ArrayList;
import java.util.Scanner;

public class Java_board {
	static ArrayList<Article> articles;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		articles = new ArrayList<>();

		Article a1 = new Article(1, "제목1", "내용1");
		Article a2 = new Article(2, "제목2", "내용2");
		Article a3 = new Article(3, "제목3", "내용3");

		articles.add(a1);
		articles.add(a2);
		articles.add(a3);

		int no = 4;

		while (true) {
			System.out.print("명령어 입력: ");
			String str = sc.next();

			if (str.equals("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			if (str.equals("add")) {

				Article a = new Article();

				a.setId(no);
				no++;
				System.out.print("게시물 제목 입력 : ");
				String title = sc.next();
				a.setTitle(title);

				System.out.print("게시물 내용 입력 : ");
				String body = sc.next();
				a.setBody(body);

				articles.add(a);
				System.out.println("게시물이 등록되었습니다.");

			}
			if (str.equals("list")) {
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					System.out.println("번호 : " + article.getId());
					System.out.println("제목 : " + article.getTitle());
					System.out.println("===================");

				}
			}
			if (str.equals("update")) {
				System.out.print("수정할 게시물 선택 : ");
				int targetId = sc.nextInt();

				for (int i = 0; i < articles.size(); i++) {
//					Article article = articles.get(i);
//					int id = article.getId();

					int id = articles.get(i).getId();
					if (id == targetId) {
						System.out.print("게시물 제목 입력 : ");
						String newTitle = sc.next();
						System.out.print("게시물 내용 입력 : ");
						String newBody = sc.next();

						Article newArticle = new Article();
						newArticle.setId(id);
						newArticle.setTitle(newTitle);
						newArticle.setBody(newBody);
						break;

					}
				}
			}
			if (str.equals("delete")) {
				System.out.print("몇번 게시물을 지우시겠습니까 : ");
				int targetId = sc.nextInt();
				int existFlag = 2; // 1. 있음 2. 없음

				for (int i = 0; i < articles.size(); i++) {
					int id = articles.get(i).getId();
					if (id == targetId) {
						existFlag = 1;
						articles.remove(i);
						break;
					}
				}

				if (existFlag == 2) {

					System.out.println("게시물이 존재하지 않습니다.");
				} else {

					System.out.println(targetId + "번 게시물이 삭제되었습니다.");
				}

			}
			if (str.equals("read")) {
				System.out.print("게시물 번호를 선택해주세요 : ");
				int targetId = sc.nextInt();
				int existFlag = 2;

				for (int i = 0; i < articles.size(); i++) {
					int id = articles.get(i).getId();

					if (id == targetId) {
						existFlag = 1;
						System.out.println("=====" + id + "번 게시물" + "=====");
						System.out.println("번호 : " + articles.get(i).getId());
						System.out.println("제목 : " + articles.get(i).getTitle());
						System.out.println("내용 : " + articles.get(i).getBody());
						System.out.println("===================");
						break;
					}
				}

				if (existFlag == 2) {

					System.out.println("게시물이 존재하지 않습니다.");
				}
			}
		}
	}

	// index 버전
	public static int getArticleIndexById(int targetId) {
		for (int i = 0; i < articles.size(); i++) {
			int id = articles.get(i).getId();

			if (id == targetId) {
				return i;
			}
		}

		return -1;
	}

	// Article 버전
	public static int getArticleById(int targetId) {
		for (int i = 0; i < articles.size(); i++) {
			int id = articles.get(i).getId();

			if (id == targetId) {
				return i;
			}
		}

		return -1;
	}
}