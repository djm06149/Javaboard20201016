import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Java_board {
	static ArrayList<Article> articles;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");

		articles = new ArrayList<>();

		Article a1 = new Article(1, "제목1", "내용1", "익명");
		Article a2 = new Article(2, "제목2", "내용2", "익명");
		Article a3 = new Article(3, "제목3", "내용3", "익명");

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
				a.setNickname("익명");

				articles.add(a);
				System.out.println("게시물이 등록되었습니다.");
			}
			if (str.equals("list")) {
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					System.out.println("번호 : " + article.getId());
					System.out.println("제목 : " + article.getTitle());
					System.out.println("작성자 : " + article.getNickname());
					System.out.println("등록날짜 : " + date.format(today));
					System.out.println("조회수 : " + article.getHit());
					System.out.println("======================");
				}
			}
			if (str.equals("update")) {

// index 버전
//		System.out.println("수정할 게시물 선택 : ");
//		int targetId = sc.nextInt();
//			if (targetId == -1) {
//				System.out.println("없는 게시물입니다.");
//				} else {
//					System.out.println("게시물 제목을 입력해주세요 :");
//					String newTitle = sc.next();			
//					System.out.println("게시물 내용을 입력해주세요 :");
//					String newBody = sc.next();				
//					Article newArticle = new Article();
//					newArticle.setId(targetId);
//					newArticle.setTitle(newTitle);
//					newArticle.setBody(newBody);				
//					articles.set(targetId, newArticle);
//					break;
//								}

				System.out.println("수정할 게시물 선택 : ");
				int targetId = sc.nextInt();
				Article target = getArticleById(targetId);
				if (target == null) {
					System.out.println("없는 게시물입니다.");
				} else {
					System.out.println("게시물 제목을 입력해주세요 :");
					String newTitle = sc.next();
					System.out.println("게시물 내용을 입력해주세요 :");
					String newBody = sc.next();
					target.setTitle(newTitle);
					target.setBody(newBody);
					break;
				}
			}
			if (str.equals("delete")) {
				System.out.println("삭제할 게시물 선택 : ");
				int targetId = sc.nextInt();
				Article target = getArticleById(targetId);
				if (target == null) {
					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					articles.remove(target);
				}
			}
			if (str.equals("read")) {
				System.out.println("게시물 번호 선택 : ");
				int targetId = sc.nextInt();
				Article target = getArticleById(targetId);
				if (target == null) {
					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					target.setHit(target.getHit() + 1);
					System.out.println("==== " + target.getId() + " ====");
					System.out.println("번호 : " + target.getId());
					System.out.println("제목 : " + target.getTitle());
					System.out.println("내용 : " + target.getBody());
					System.out.println("===============");
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
	public static Article getArticleById(int targetId) {
		for (int i = 0; i < articles.size(); i++) {
			int id = articles.get(i).getId();

			if (id == targetId) {
				return articles.get(i);
			}
		}
		return null;
	}
}