import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LikeDao {
	private ArrayList<Like> likes;
	private int no = 1;

	public LikeDao() {
		likes = new ArrayList<>();
	}

	public void insertLike(Like l) {
		l.setId(no);
		no++;
		l.setRegDate(Util.getCurrentDate());

		likes.add(l);
	}

	public Like getLikeByArticleIdAndMemberId(int aid, int mid) {
		for (int i = 0; i < likes.size(); i++) {
			Like like = likes.get(i);
			if (like.getParentId() == aid && like.getMemberId() == mid) {
				return like;
			}
		}
		return null;
	}

	public void removeLike(Like rst) {
		likes.remove(rst);

	}

	public int getLikeCount(int id) {
		int cnt = 0;
		for (int i = 0; i < likes.size(); i++) {
			Like like = likes.get(i);
			if (like.getParentId() == id) {
				cnt++;
			}
		}
		return cnt;
	}

}
