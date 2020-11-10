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
				System.out.print("명령어 입력 : ");
			} else {
				System.out.print(
						"명령어 입력[" + loginedMember.getLoginId() + "(" + loginedMember.getNickname() + ")" + "] : ");
			}
			String cmd = sc.nextLine();
			if (cmd.equals("exit")) {
				System.out.println("종료");
				break;
			}
			if (cmd.equals("help")) {
				System.out.println("article [add: 게시물 추가 / list : 게시물 목록 조회 / read : 게시물 조회 / search : 검색]");
				System.out.println(
						"member [signup : 회원가입 / signin : 로그인 / findpass : 비밀번호 찾기 / findid : 아이디 찾기 / logout : 로그아웃 / myinfo : 나의 정보 확인및 수정]");
			}
			if (cmd.equals("article add")) {

				if (!isLogin()) {
					continue;
				}

				Article a = new Article();

				System.out.print("게시물 제목을 입력해주세요 :");
				String title = sc.nextLine();
				a.setTitle(title);

				System.out.print("게시물 내용을 입력해주세요 :");
				String body = sc.nextLine();
				a.setBody(body);
				a.setMid(loginedMember.getId());

				articleDao.insertArticle(a);
				System.out.println("게시물이 등록되었습니다.");

			}
			if (cmd.equals("article list")) {
				ArrayList<Article> articles = articleDao.getArticles();

				printArticles(articles, 1);

			}
			if (cmd.equals("article update")) {

				System.out.print("수정할 게시물 선택 : ");
				int targetId = Integer.parseInt(sc.nextLine());

				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("없는 게시물입니다.");
				} else {
					System.out.print("게시물 제목을 입력해주세요 :");
					String newTitle = sc.nextLine();

					System.out.print("게시물 내용을 입력해주세요 :");
					String newBody = sc.nextLine();

					target.setTitle(newTitle);
					target.setBody(newBody);

					break;
				}
			}
			if (cmd.equals("article delete")) {
				ArrayList<Article> articles = articleDao.getArticles();
				System.out.print("삭제할 게시물 선택 : ");
				int targetId = Integer.parseInt(sc.nextLine());
				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					articleDao.removeArticle(target);
				}
			}
			if (cmd.equals("article read")) {
				System.out.print("상세보기할 게시물 선택 : ");
				int targetId = Integer.parseInt(sc.nextLine());
				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					target.setHit(target.getHit() + 1);
					printArticle(target);

					while (true) {
						System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) :");
						int readCmd = Integer.parseInt(sc.nextLine());
						if (readCmd == 1) {
							Comment c = new Comment();

							System.out.print("댓글 내용을 입력해주세요:");
							String body = sc.nextLine();
							c.setParentId(target.getId());
							c.setBody(body);
							c.setNickname("익명");

							commentDao.insertComment(c);

							System.out.println("댓글이 등록되었습니다.");
							printArticle(target);

						} else if (readCmd == 2) {

							if (!isLogin()) {
								continue;
							}

							Like rst = likeDao.getLikeByArticleIdAndMemberId(target.getId(), loginedMember.getId());

							if (rst == null) {
								Like like = new Like(target.getId(), loginedMember.getId());
								likeDao.insertLike(like);
								target.setLikeCnt(target.getLikeCnt() + 1);

								System.out.println("좋아요를 체크했습니다.");

							} else {
								likeDao.removeLike(rst);
								System.out.println("좋아요를 해제했습니다.");
								target.setLikeCnt(target.getLikeCnt() - 1);
							}

						} else if (readCmd == 3) {

							if (!isLogin() || isMyArticle(target)) {
								continue;
							}
							System.out.print("게시물 제목을 입력해주세요 :");
							String newTitle = sc.nextLine();

							System.out.print("게시물 내용을 입력해주세요 :");
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
				System.out.print("검색 항목을 선택해주세요 (1. 제목, 2. 내용, 3. 제목 + 내용, 4. 작성자) : ");
				int flag = Integer.parseInt(sc.nextLine());
				System.out.print("검색 키워드를 입력해주세요 : ");
				String keyword = sc.nextLine();
				ArrayList<Article> searchedArticles;

				searchedArticles = articleDao.getSearchedArticlesByFlag(flag, keyword);

				printArticles(searchedArticles, 1);
			}
			if (cmd.equals("article sort")) {
				System.out.print("정렬 대상을 선택해주세요. (like : 좋아요,  hit : 조회수) : ");
				String sortType = sc.nextLine();
				System.out.print("정렬방법을 선택해주세요.(asc : 오름차순, desc : 내림차순) : ");
				String sortOrder = sc.nextLine();
				MyComparator comp = new MyComparator();
				comp.sortOrder = sortOrder;
				comp.sortType = sortType;
				// 조회수로 오름차순
				ArrayList<Article> articles = articleDao.getArticles();
				Collections.sort(articles, comp);
				printArticles(articles, 1);

			}
			if (cmd.equals("member signup")) {
				System.out.println("======== 회원가입을 진행합니다.========");
				Member m = new Member();

				System.out.print("아이디를 입력해주세요 :");
				String id = sc.nextLine();
				m.setLoginId(id);

				System.out.print("비밀번호를 입력해주세요 :");
				String pw = sc.nextLine();
				m.setLoginPw(pw);

				System.out.print("닉네임을 입력해주세요 :");
				String nick = sc.nextLine();
				m.setNickname(nick);

				memberDao.insertMember(m);
				System.out.println("======== 회원가입이 완료되었습니다. ========");
			}
			if (cmd.equals("member signin")) {
				System.out.print("아이디 :");
				String id = sc.nextLine();

				System.out.print("비밀번호 :");
				String pw = sc.nextLine();

				Member member = memberDao.getMemberByLoginIdAndLoginPw(id, pw);
				if (member == null) {
					System.out.println("비밀번호를 틀렸거나 잘못된 회원정보입니다.");
				} else {
					loginedMember = member;
					System.out.println(loginedMember.getNickname() + "님 안녕하세요!!");
				}

			}
			if (cmd.equals("member logout")) {

				if (!isLogin()) {
					continue;
				}

				loginedMember = null;
				System.out.println("로그아웃 되셨습니다.");

			}
			if (cmd.equals("article page")) {
				ArrayList<Article> articles = articleDao.getArticles();
				int currentPageNo = 1;
				printArticles(articles, currentPageNo);

				System.out.println(
						"페이징 명령어를 입력해주세요 ((prev : 이전,  next : 다음, prevPage : 이전페이지, nextPage : 다음페이지, count : 페이지당 게시물 수  go : 선택,  back : 뒤로가기)");

				while (true) {
					String pageCmd = sc.nextLine();
					if (pageCmd.equals("next")) {
						currentPageNo++;
					} else if (pageCmd.equals("prev")) {
						currentPageNo--;
					} else if (pageCmd.equals("nextPage")) {
						currentPageNo += 5;
					} else if (pageCmd.equals("prevPage")) {
						currentPageNo -= 5;
					} else if (pageCmd.equals("count")) {
						System.out.println("출력할 게시물의 개수를 입력해주세요 : ");
						int count = sc.nextInt();
						printArticles(articles, currentPageNo);
					} else if (pageCmd.equals("go")) {
						currentPageNo = Integer.parseInt(sc.nextLine());
					} else if (pageCmd.equals("back")) {
						break;
					}
				}
			}
		}
	}

	private void printArticles(ArrayList<Article> articleList, int currentPageNo) {
		int totalCntOfItems = articleList.size(); // 전체 게시물 개수
		int startPageNo = 1; // 시작 페이지 번호
		int itemsCntPerPage = 3; // 페이지당 출력 게시물 개수
		int pageCntPerBlock = 5; // 한 페이지 블록 당 페이지 개수
		int endPageNo = (int) Math.ceil((double) totalCntOfItems / itemsCntPerPage); // 마지막 페이지 번호

		// 현재 페이지가 시작페이지보다 작으면 안됨
		if (currentPageNo < startPageNo) {
			currentPageNo = startPageNo;
		}
		// 현재 페이지가 마지막페이지보다 크면 안됨
		if (currentPageNo > endPageNo) {
			currentPageNo = endPageNo;
		}

		int currentPageBlock = (int) Math.ceil((double) currentPageNo / pageCntPerBlock); // 현재 페이지 블록
		int startPageNoInBlock = (currentPageBlock - 1) * pageCntPerBlock + 1; // 현재 페이지 블록의 시작 페이지 번호
		int endPageNoInBlock = startPageNoInBlock + pageCntPerBlock - 1;// // 현재 페이지 블록의 마지막 페이지 번호

		// 페이지 번호가 마지막 페이지를 넘으면 안됨
		if (endPageNoInBlock > endPageNo) {
			endPageNoInBlock = endPageNo;
		}
		// 해당 페이지의 게시물 목록의 첫 인덱스
		int startIndex = (currentPageNo - 1) * itemsCntPerPage;

		// 해당 페이지의 게시물 목록의 마지막 인덱스
		int endIndex = startIndex + itemsCntPerPage;

		// 페이지의 마지막 인덱스가 저장소의 마지막 인덱스보다 크면 안됨
		if (endIndex > totalCntOfItems) {
			endIndex = totalCntOfItems;
		}
		System.out.println(endPageNoInBlock);
		for (int i = startIndex; i < endIndex; i++) {
			Article article = articleList.get(i);
			System.out.println("번호 : " + article.getId());
			System.out.println("제목 : " + article.getTitle());
			System.out.println("등록날짜 : " + article.getRegDate());
			Member regMember = memberDao.getMemberById(article.getMid());
			System.out.println("작성자 : " + article.getMid());
			System.out.println("조회수 : " + article.getHit());
			System.out.println("좋아요 : " + article.getLikeCnt());
			System.out.println("===================");
		}
		for (int i = startPageNoInBlock; i <= endPageNoInBlock; i++) {

			if (i == currentPageNo) {
				System.out.print("[" + i + "] ");
			} else {
				System.out.print(i + " ");
			}
		}
		System.out.println("");

	}

	private void printComments(ArrayList<Comment> commentList) {
		for (int i = 0; i < commentList.size(); i++) {
			Comment comment = commentList.get(i);
			System.out.println("내용 : " + comment.getBody());
			System.out.println("작성자 : " + comment.getNickname());
			System.out.println("등록날짜 : " + comment.getRegDate());
			System.out.println("===================");
		}
	}

	private void printArticle(Article target) {
		System.out.println("==== " + target.getId() + " ====");
		System.out.println("번호 : " + target.getId());
		System.out.println("제목 : " + target.getTitle());
		System.out.println("내용 : " + target.getBody());
		Member regMember = memberDao.getMemberById(target.getMid());
		System.out.println("작성자 : " + target.getBody());
		System.out.println("등록날짜 : " + target.getRegDate());
		System.out.println("조회수 : " + target.getHit());
		System.out.println("좋아요 : " + target.getLikeCnt());
		System.out.println("===============");
		System.out.println("================댓글==============");

		ArrayList<Comment> comments = commentDao.getCommentsByParentId(target.getId());
		printComments(comments);
	}

	private boolean isLogin() {
		if (loginedMember == null) {
			System.out.println("로그인이 필요한 기능입니다.");
			return false;
		} else {
			return true;
		}
	}

	private boolean isMyArticle(Article article) {

		if (loginedMember.getId() != article.getMid()) {
			System.out.println("자신의 게시물만 수정가능합니다.");
			return false;
		}

		return true;
	}

	class MyComparator implements Comparator<Article> {

		String sortOrder = "asc";
		String sortType = "hit";

		@Override
		public int compare(Article o1, Article o2) {
			int c1 = 0;
			int c2 = 0;
			if (sortType.equals("hit")) {
				c1 = o1.getHit();
				c2 = o2.getHit();
			} else if (sortType.equals("hit")) {
				c1 = o1.getLikeCnt();
				c2 = o2.getLikeCnt();
			}

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