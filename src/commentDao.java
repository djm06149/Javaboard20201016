import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CommentDao {
	private static ArrayList<Comment> comments;
	private int no = 1;

	public CommentDao() {
		comments = new ArrayList<>(); 
//		replies = new ArrayList<>();
//		Reply a1 = new Reply(1, "안녕하세요", "내용1", "익명", getCurrentDate());
//		Reply a2 = new Reply(2, "반갑습니다.", "내용2", "익명", getCurrentDate());
//		Reply a3 = new Reply(3, "안녕", "내용3", "익명", getCurrentDate());
//
//		replies.add(a1);
//		replies.add(a2);
//		replies.add(a3);
	}

	public void insertComment(Comment a) {
		a.setId(no);
		no++;
		a.setRegDate(getCurrentDate());

		comments.add(a);
	}
//
//	public void removeArticle(Article a) {
//		articles.remove(a);
//	}
//
	private static String getCurrentDate() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
		Date time = new Date();
		String time1 = format1.format(time);

		return time1;
	}
//
//	public ArrayList<Article> getSearchedArticlesByFlag(int flag, String keyword) {
//
//		ArrayList<Article> searchedArticles = new ArrayList<>();
//
//		for (int i = 0; i < articles.size(); i++) {
//			Article article = articles.get(i);
//			String str = article.getPropertiesByFlag(flag);
//			if (str.contains(keyword)) {
//				searchedArticles.add(article);
//			}
//		}
//
//		return searchedArticles;
//
//	}
//
//	// Article 버전
//	public static Article getArticleById(int targetId) {
//		for (int i = 0; i < articles.size(); i++) {
//			int id = articles.get(i).getId();
//			if (id == targetId) {
//				return articles.get(i);
//			}
//		}
//
//		return null;
//	}
//
	public ArrayList<Comment> getComment() {
		return comments;
	}

	public ArrayList<Comment> getCommentsByParentId(int parentId) {
		ArrayList<Comment> searchedComments = new ArrayList<>();
		for (int i = 0; i < comments.size(); i++) {
			Comment comment = comments.get(i);
			if (comment.getParentId() == parentId) {
				searchedComments.add(comment);
			}
		}
		return comments;
	}
}

//
//	public ArrayList<Article> getSearchedArticlesByBody(String keyword) {
//		ArrayList<Article> searchedArticles = new ArrayList<>();
//
//		for (int i = 0; i < articles.size(); i++) {
//			Article article = articles.get(i);
//			String str = article.getBody(); // 각 게시물 제목
//			if (str.contains(keyword)) {
//				searchedArticles.add(article);
//			}
//		}
//
//		return searchedArticles;
//	}