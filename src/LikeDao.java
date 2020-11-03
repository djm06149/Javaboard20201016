import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LikeDao {
	private ArrayList<Like> likes;
	private int no = 1;
	
	public LikeDao() {
		likes = new ArrayList<>();
//		replies = new ArrayList<>();
//		Reply a1 = new Reply(1, "안녕하세요", "내용1", "익명", getCurrentDate());
//		Reply a2 = new Reply(2, "반갑습니다.", "내용2", "익명", getCurrentDate());
//		Reply a3 = new Reply(3, "안녕", "내용3", "익명", getCurrentDate());
//
//		replies.add(a1);
//		replies.add(a2);
//		replies.add(a3);
	}

	public void insertLike(Like l) {
		l.setId(no);
		no++;
		l.setRegDate(getCurrentDate());

		likes.add(l);
	}
	private static String getCurrentDate() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
		Date time = new Date();
		String time1 = format1.format(time);

		return time1;
	}

}
