import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class App {

	MemberDao memberDao = new MemberDao();
	CommentDao commentDao = new CommentDao();
	ArticleDao articleDao = new ArticleDao();
	LikeDao likeDao = new LikeDao();
	Member loginedMember = null;

	public void start() {
		Scanner sc = new Scanner(System.in);

		while (true) {
			if (loginedMember == null) {
				System.out.print("��ɾ� �Է� : ");
			} else {
				System.out.print(
						"��ɾ� �Է�[" + loginedMember.getLoginId() + "(" + loginedMember.getNickname() + ")" + "] : ");
			}
			String cmd = sc.nextLine();
			if (cmd.equals("exit")) {
				System.out.println("����");
				break;
			}
			if (cmd.equals("help")) {
				System.out.println("article [add: �Խù� �߰� / list : �Խù� ��� ��ȸ / read : �Խù� ��ȸ / search : �˻�]");
				System.out.println(
						"member [signup : ȸ������ / signin : �α��� / findpass : ��й�ȣ ã�� / findid : ���̵� ã�� / logout : �α׾ƿ� / myinfo : ���� ���� Ȯ�ι� ����]");
			}
			if (cmd.equals("article add")) {

				if (!isLogin()) {
					continue;
				}

				Article a = new Article();

				System.out.print("�Խù� ������ �Է����ּ��� :");
				String title = sc.nextLine();
				a.setTitle(title);

				System.out.print("�Խù� ������ �Է����ּ��� :");
				String body = sc.nextLine();
				a.setBody(body);
				a.setMid(loginedMember.getId());

				articleDao.insertArticle(a);
				System.out.println("�Խù��� ��ϵǾ����ϴ�.");

			}
			if (cmd.equals("article list")) {
				ArrayList<Article> articles = articleDao.getArticles();

				printArticles(articles);

			}
			if (cmd.equals("article update")) {

				System.out.print("������ �Խù� ���� : ");
				int targetId = Integer.parseInt(sc.nextLine());

				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("���� �Խù��Դϴ�.");
				} else {
					System.out.print("�Խù� ������ �Է����ּ��� :");
					String newTitle = sc.nextLine();

					System.out.print("�Խù� ������ �Է����ּ��� :");
					String newBody = sc.nextLine();

					target.setTitle(newTitle);
					target.setBody(newBody);

					break;
				}
			}
			if (cmd.equals("article delete")) {
				ArrayList<Article> articles = articleDao.getArticles();
				System.out.print("������ �Խù� ���� : ");
				int targetId = Integer.parseInt(sc.nextLine());
				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("�Խù��� �������� �ʽ��ϴ�.");
				} else {
					articleDao.removeArticle(target);
				}
			}
			if (cmd.equals("article read")) {
				System.out.print("�󼼺����� �Խù� ���� : ");
				int targetId = Integer.parseInt(sc.nextLine());
				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("�Խù��� �������� �ʽ��ϴ�.");
				} else {
					target.setHit(target.getHit() + 1);
					printArticle(target);

					while (true) {
						System.out.print("�󼼺��� ����� �������ּ���(1. ��� ���, 2. ���ƿ�, 3. ����, 4. ����, 5. �������) :");
						int readCmd = Integer.parseInt(sc.nextLine());
						if (readCmd == 1) {
							Comment c = new Comment();

							System.out.print("��� ������ �Է����ּ���:");
							String body = sc.nextLine();
							c.setParentId(target.getId());
							c.setBody(body);
							c.setNickname("�͸�");

							commentDao.insertComment(c);

							System.out.println("����� ��ϵǾ����ϴ�.");
							printArticle(target);

						} else if (readCmd == 2) {

							if (!isLogin()) {
								continue;
							}

							Like rst = likeDao.getLikeByArticleIdAndMemberId(target.getId(), loginedMember.getId());

							if (rst == null) {
								// ����
								Like like = new Like(target.getId(), loginedMember.getId());
								likeDao.insertLike(like);
								System.out.println("���ƿ並 üũ�߽��ϴ�.");

							} else {
								likeDao.removeLike(rst);
								System.out.println("���ƿ並 �����߽��ϴ�.");
							}

						} else if (readCmd == 3) {

							if (!isLogin() || isMyArticle(target)) {
								continue;
							}
							System.out.print("�Խù� ������ �Է����ּ��� :");
							String newTitle = sc.nextLine();

							System.out.print("�Խù� ������ �Է����ּ��� :");
							String newBody = sc.nextLine();

							target.setTitle(newTitle);
							target.setBody(newBody);

							printArticle(target);

						} else if (readCmd == 4) {

							if (!isLogin() || isMyArticle(target)) {
								continue;
							}

							articleDao.removeArticle(target);
							break;

						} else if (readCmd == 5) {
							break;
						}
					}
				}
			}
			if (cmd.equals("article search")) {
				System.out.print("�˻� �׸��� �������ּ��� (1. ����, 2. ����, 3. ���� + ����, 4. �ۼ���) : ");
				int flag = Integer.parseInt(sc.nextLine());
				System.out.print("�˻� Ű���带 �Է����ּ��� : ");
				String keyword = sc.nextLine();
				ArrayList<Article> searchedArticles;

				searchedArticles = articleDao.getSearchedArticlesByFlag(flag, keyword);

				printArticles(searchedArticles);
			}
			if (cmd.equals("article sort")) {
				System.out.println("���� ����� �������ּ���. (like : ���ƿ�,  hit : ��ȸ��) :");
				String sortType = sc.nextLine();
				System.out.print("���Ĺ���� �������ּ���.(asc : ��������, desc : ��������)");
				String sortOrder = sc.nextLine();
				MyComparator comp = new MyComparator();
				comp.sortOrder = sortOrder;
				// ��ȸ���� ��������
				ArrayList<Article> articles = articleDao.getArticles();
				Collections.sort(articles, comp);
				printArticles(articles);

			}
			if (cmd.equals("member signup")) {
				System.out.println("======== ȸ�������� �����մϴ�.========");
				Member m = new Member();

				System.out.print("���̵� �Է����ּ��� :");
				String id = sc.nextLine();
				m.setLoginId(id);

				System.out.print("��й�ȣ�� �Է����ּ��� :");
				String pw = sc.nextLine();
				m.setLoginPw(pw);

				System.out.print("�г����� �Է����ּ��� :");
				String nick = sc.nextLine();
				m.setNickname(nick);

				memberDao.insertMember(m);
				System.out.println("======== ȸ�������� �Ϸ�Ǿ����ϴ�.========");
			}
			if (cmd.equals("member signin")) {
				System.out.print("���̵� :");
				String id = sc.nextLine();

				System.out.print("��й�ȣ :");
				String pw = sc.nextLine();

				Member member = memberDao.getMemberByLoginIdAndLoginPw(id, pw);
				if (member == null) {
					System.out.println("��й�ȣ�� Ʋ�Ȱų� �߸��� ȸ�������Դϴ�.");
				} else {
					loginedMember = member;
					System.out.println(loginedMember.getNickname() + "�� �ȳ��ϼ���!!");
				}

			}
			if (cmd.equals("member logout")) {

				if (!isLogin()) {
					continue;
				}

				loginedMember = null;
				System.out.println("�α׾ƿ� �Ǽ̽��ϴ�.");

			}
		}
	}

	private void printArticles(ArrayList<Article> articleList) {
		for (int i = 0; i < articleList.size(); i++) {
			Article article = articleList.get(i);
			System.out.println("��ȣ : " + article.getId());
			System.out.println("���� : " + article.getTitle());
			System.out.println("��ϳ�¥ : " + article.getRegDate());
			Member regMember = memberDao.getMemberById(article.getMid());
			System.out.println("�ۼ��� : " + article.getMid());
			System.out.println("��ȸ�� : " + article.getHit());
			System.out.println("===================");
		}
	}

	private void printComments(ArrayList<Comment> commentList) {
		for (int i = 0; i < commentList.size(); i++) {
			Comment comment = commentList.get(i);
			System.out.println("���� : " + comment.getBody());
			System.out.println("�ۼ��� : " + comment.getNickname());
			System.out.println("��ϳ�¥ : " + comment.getRegDate());
			System.out.println("===================");
		}
	}

	private void printArticle(Article target) {
		System.out.println("==== " + target.getId() + " ====");
		System.out.println("��ȣ : " + target.getId());
		System.out.println("���� : " + target.getTitle());
		System.out.println("���� : " + target.getBody());
		Member regMember = memberDao.getMemberById(target.getMid());
		System.out.println("�ۼ��� : " + target.getBody());
		System.out.println("��ϳ�¥ : " + target.getRegDate());
		System.out.println("��ȸ�� : " + target.getHit());
		int likeCnt = likeDao.getLikeCount(target.getId());
		System.out.println("���ƿ� : " + likeCnt);
		System.out.println("===============");
		System.out.println("================���==============");

		ArrayList<Comment> comments = commentDao.getCommentsByParentId(target.getId());
		printComments(comments);
	}

	private boolean isLogin() {
		if (loginedMember == null) {
			System.out.println("�α����� �ʿ��� ����Դϴ�.");
			return false;
		} else {
			return true;
		}
	}

	private boolean isMyArticle(Article article) {

		if (loginedMember.getId() != article.getMid()) {
			System.out.println("�ڽ��� �Խù��� ���������մϴ�.");
			return false;
		}

		return true;
	}

	class MyComparator implements Comparator<Article> {

		String sortOrder = "asc";
		String sortType = "hit";

		@Override
		public int compare(Article o1, Article o2) {
			int c1 = o1.getHit();
			int c2 = o2.getHit();
			
			if (sortOrder.equals("asc")) {
				if (c1 > c2) {
					return 1;
				}
				return -1;
			} else {
				if (c1 < c2) {
					return 1;

				}
				return -1;
			}
		}
	}

}