import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Java_board {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArticleDao dao = new ArticleDao();

		while (true) {
			System.out.print("명령어 입력: ");
			String str = sc.next();

			if (str.equals("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			if (str.equals("add")) {

				Article a = new Article();

				System.out.print("게시물 제목 입력 : ");
				String title = sc.next();
				a.setTitle(title);

				System.out.print("게시물 내용 입력 : ");
				String body = sc.next();
				a.setBody(body);

				a.setNickname("익명");

				dao.insertArticle(a);
				System.out.println("게시물이 등록되었습니다.");
			}
			if (str.equals("list")) {
				ArrayList<Article> articles = dao.getArticles();

				printArticles(articles);
			}
			if (str.equals("update")) {

				System.out.println("수정할 게시물 선택 : ");
				int targetId = sc.nextInt();
				Article target = dao.getArticleById(targetId);
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
				ArrayList<Article> articles = dao.getArticles();
				int targetId = sc.nextInt();
				Article target = dao.getArticleById(targetId);
				if (target == null) {
					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					dao.removeArticle(target);
				}
			}
			if (str.equals("read")) {
				System.out.println("게시물 번호 선택 : ");
				int targetId = sc.nextInt();
				Article target = dao.getArticleById(targetId);
				if (target == null) {
					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					target.setHit(target.getHit() + 1);
					System.out.println("==== " + target.getId() + " ====");
					System.out.println("번호 : " + target.getId());
					System.out.println("제목 : " + target.getTitle());
					System.out.println("내용 : " + target.getBody());
					System.out.println("===============");
					while (true) {
						System.out.println("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) :");
						int readStr = sc.nextInt();
						if (readStr == 1) {
							System.out.println("댓글 기능");
						} else if (readStr == 2) {
							System.out.println("좋아요 기능");
						} else if (readStr == 3) {
							System.out.println("수정 기능");
						} else if (readStr == 4) {
							System.out.println("삭제 기능");
						} else if (readStr == 5) {
							break;
						}
					}
				}
			}
			if (str.equals("search")) {
				System.out.print("검색항목을 선택해주세요 (1. 제목 2. 내용 3. 제목 + 내용 4. 작성자) : ");

				int flag = sc.nextInt();
				System.out.print("검색 키워드를 입력해주세요 : ");
				String keyword = sc.next();
				ArrayList<Article> searchedArticles;

				searchedArticles = dao.getSearchedArticlesByFlag(flag, keyword);

				printArticles(searchedArticles);
			}
		}
	}

	private static void printArticles(ArrayList<Article> articleList) {
		for (int i = 0; i < articleList.size(); i++) {
			Article article = articleList.get(i);
			System.out.println("번호 : " + article.getId());
			System.out.println("제목 : " + article.getTitle());
			System.out.println("작성자 : " + article.getNickname());
			System.out.println("등록날짜 : " + article.getRegDate());
			System.out.println("조회수 : " + article.getHit());
			System.out.println("======================");

		}
	}
}
