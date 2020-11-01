import java.util.ArrayList;
import java.util.Scanner;

public class Java_board {

	static MemberDao memberDao = new MemberDao();
	static CommentDao CommentDao = new CommentDao();
	static ArticleDao articleDao = new ArticleDao();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Member loginedMember = null;

		while (true) {
			if(loginedMember == null) {
				System.out.print("명령어 입력: ");				
			} else {
				System.out.println("명령어 입력[" + loginedMember.getLoginId() + "(" +loginedMember.getNickname()+ ")" +"] :");
			}
			
			
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

				articleDao.insertArticle(a);
				System.out.println("게시물이 등록되었습니다.");
			}
			if (str.equals("list")) {
				ArrayList<Article> articles = articleDao.getArticles();

				printArticles(articles);
			}
			if (str.equals("update")) {

				System.out.println("수정할 게시물 선택 : ");
				int targetId = sc.nextInt();
				Article target = articleDao.getArticleById(targetId);
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
				ArrayList<Article> articles = articleDao.getArticles();
				int targetId = sc.nextInt();
				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					articleDao.removeArticle(target);
				}
			}
			if (str.equals("read")) {
				System.out.print("게시물 번호 선택 : ");
				int targetId = sc.nextInt();
				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					target.setHit(target.getHit() + 1);
					printArticle(target);

					while (true) {
						System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) :");
						int readStr = sc.nextInt();

						if (readStr == 1) {

							Comment c = new Comment();

							System.out.print("댓글 내용을 입력해주세요:");
							String body = sc.next();
							c.setParentId(target.getId());
							c.setBody(body);
							c.setNickname("익명");

							CommentDao.insertComment(c);
							System.out.println("댓글이 등록되었습니다.");
							printArticle(target);

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

				searchedArticles = articleDao.getSearchedArticlesByFlag(flag, keyword);

				printArticles(searchedArticles);
			}
			if (str.equals("signup")) {
				Member m = new Member();
				System.out.println("=== 회원가입을 시작합니다 ===");

				System.out.print("아이디를 입력하세요 : ");
				String id = sc.next();
				m.setLoginId(id);

				System.out.print("비밀번호를 입력하세요 : ");
				String pw = sc.next();
				m.setLoginPw(pw);

				System.out.print("닉네임을 입력해주세요 : ");
				String nickname = sc.next();
				m.setNickname(nickname);

				memberDao.insertMember(m);
				System.out.println("=== 회원가입이 완료되었습니다 ===");

			}
			if (str.equals("signin")) {
				System.out.print("아이디 : ");
				String id = sc.next();

				System.out.print("비밀번호 : ");
				String pw = sc.next();

				Member member = memberDao.getMemberByLoginIdAndLoginPw(id, pw);
				if (member == null) {
					System.out.println("비밀번호를 틀렸거나 잘못된 회원정보입니다.");
				} else {
					loginedMember = member;
					System.out.println(loginedMember.getNickname() + "님 안녕하세요!!");
				}

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

	private static void printComments(ArrayList<Comment> commentList) {
		for (int i = 0; i < commentList.size(); i++) {
			Comment comment = commentList.get(i);
			System.out.println("내용 : " + comment.getBody());
			System.out.println("작성자 : " + comment.getNickname());
			System.out.println("등록날짜 : " + comment.getRegDate());
			System.out.println("===================");
		}
	}

	private static void printArticle(Article target) {
		System.out.println("==== " + target.getId() + " ====");
		System.out.println("번호 : " + target.getId());
		System.out.println("제목 : " + target.getTitle());
		System.out.println("내용 : " + target.getBody());
		System.out.println("등록날짜 : " + target.getRegDate());
		System.out.println("조회수 : " + target.getHit());
		System.out.println("===============");
		System.out.println("================댓글==============");

		ArrayList<Comment> comments = CommentDao.getCommentsByParentId(target.getId());
		printComments(comments);
	}
}