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
				System.out.print("��ɾ� �Է�: ");				
			} else {
				System.out.println("��ɾ� �Է�[" + loginedMember.getLoginId() + "(" +loginedMember.getNickname()+ ")" +"] :");
			}
			
			
			String str = sc.next();

			if (str.equals("exit")) {
				System.out.println("���α׷��� �����մϴ�.");
				break;
			}
			if (str.equals("add")) {

				Article a = new Article();

				System.out.print("�Խù� ���� �Է� : ");
				String title = sc.next();
				a.setTitle(title);

				System.out.print("�Խù� ���� �Է� : ");
				String body = sc.next();
				a.setBody(body);

				a.setNickname("�͸�");

				articleDao.insertArticle(a);
				System.out.println("�Խù��� ��ϵǾ����ϴ�.");
			}
			if (str.equals("list")) {
				ArrayList<Article> articles = articleDao.getArticles();

				printArticles(articles);
			}
			if (str.equals("update")) {

				System.out.println("������ �Խù� ���� : ");
				int targetId = sc.nextInt();
				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("���� �Խù��Դϴ�.");
				} else {
					System.out.println("�Խù� ������ �Է����ּ��� :");
					String newTitle = sc.next();
					System.out.println("�Խù� ������ �Է����ּ��� :");
					String newBody = sc.next();

					target.setTitle(newTitle);
					target.setBody(newBody);

					break;
				}
			}
			if (str.equals("delete")) {
				System.out.println("������ �Խù� ���� : ");
				ArrayList<Article> articles = articleDao.getArticles();
				int targetId = sc.nextInt();
				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("�Խù��� �������� �ʽ��ϴ�.");
				} else {
					articleDao.removeArticle(target);
				}
			}
			if (str.equals("read")) {
				System.out.print("�Խù� ��ȣ ���� : ");
				int targetId = sc.nextInt();
				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("�Խù��� �������� �ʽ��ϴ�.");
				} else {
					target.setHit(target.getHit() + 1);
					printArticle(target);

					while (true) {
						System.out.print("�󼼺��� ����� �������ּ���(1. ��� ���, 2. ���ƿ�, 3. ����, 4. ����, 5. �������) :");
						int readStr = sc.nextInt();

						if (readStr == 1) {

							Comment c = new Comment();

							System.out.print("��� ������ �Է����ּ���:");
							String body = sc.next();
							c.setParentId(target.getId());
							c.setBody(body);
							c.setNickname("�͸�");

							CommentDao.insertComment(c);
							System.out.println("����� ��ϵǾ����ϴ�.");
							printArticle(target);

						} else if (readStr == 2) {
							System.out.println("���ƿ� ���");
						} else if (readStr == 3) {
							System.out.println("���� ���");
						} else if (readStr == 4) {
							System.out.println("���� ���");
						} else if (readStr == 5) {
							break;
						}
					}
				}
			}
			if (str.equals("search")) {
				System.out.print("�˻��׸��� �������ּ��� (1. ���� 2. ���� 3. ���� + ���� 4. �ۼ���) : ");

				int flag = sc.nextInt();
				System.out.print("�˻� Ű���带 �Է����ּ��� : ");
				String keyword = sc.next();
				ArrayList<Article> searchedArticles;

				searchedArticles = articleDao.getSearchedArticlesByFlag(flag, keyword);

				printArticles(searchedArticles);
			}
			if (str.equals("signup")) {
				Member m = new Member();
				System.out.println("=== ȸ�������� �����մϴ� ===");

				System.out.print("���̵� �Է��ϼ��� : ");
				String id = sc.next();
				m.setLoginId(id);

				System.out.print("��й�ȣ�� �Է��ϼ��� : ");
				String pw = sc.next();
				m.setLoginPw(pw);

				System.out.print("�г����� �Է����ּ��� : ");
				String nickname = sc.next();
				m.setNickname(nickname);

				memberDao.insertMember(m);
				System.out.println("=== ȸ�������� �Ϸ�Ǿ����ϴ� ===");

			}
			if (str.equals("signin")) {
				System.out.print("���̵� : ");
				String id = sc.next();

				System.out.print("��й�ȣ : ");
				String pw = sc.next();

				Member member = memberDao.getMemberByLoginIdAndLoginPw(id, pw);
				if (member == null) {
					System.out.println("��й�ȣ�� Ʋ�Ȱų� �߸��� ȸ�������Դϴ�.");
				} else {
					loginedMember = member;
					System.out.println(loginedMember.getNickname() + "�� �ȳ��ϼ���!!");
				}

			}
		}

	}

	private static void printArticles(ArrayList<Article> articleList) {
		for (int i = 0; i < articleList.size(); i++) {
			Article article = articleList.get(i);
			System.out.println("��ȣ : " + article.getId());
			System.out.println("���� : " + article.getTitle());
			System.out.println("�ۼ��� : " + article.getNickname());
			System.out.println("��ϳ�¥ : " + article.getRegDate());
			System.out.println("��ȸ�� : " + article.getHit());
			System.out.println("======================");

		}
	}

	private static void printComments(ArrayList<Comment> commentList) {
		for (int i = 0; i < commentList.size(); i++) {
			Comment comment = commentList.get(i);
			System.out.println("���� : " + comment.getBody());
			System.out.println("�ۼ��� : " + comment.getNickname());
			System.out.println("��ϳ�¥ : " + comment.getRegDate());
			System.out.println("===================");
		}
	}

	private static void printArticle(Article target) {
		System.out.println("==== " + target.getId() + " ====");
		System.out.println("��ȣ : " + target.getId());
		System.out.println("���� : " + target.getTitle());
		System.out.println("���� : " + target.getBody());
		System.out.println("��ϳ�¥ : " + target.getRegDate());
		System.out.println("��ȸ�� : " + target.getHit());
		System.out.println("===============");
		System.out.println("================���==============");

		ArrayList<Comment> comments = CommentDao.getCommentsByParentId(target.getId());
		printComments(comments);
	}
}