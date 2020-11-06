import java.util.ArrayList;

public class MemberDao {
	private ArrayList<Member> members;
	private int no = 1;

	public MemberDao() {
		members = new ArrayList<>();
		Member a1 = new Member(1, "kim123", "k1234", "±è¼­¹æ", Util.getCurrentDate());
		Member a2 = new Member(2, "lee123", "l1234", "ÀÌ¼ø½Å", Util.getCurrentDate());
		Member a3 = new Member(3, "hong123", "h1234", "È«±æµ¿", Util.getCurrentDate());

		members.add(a1);
		members.add(a2);
		members.add(a3);
	}

	public void insertMember(Member m) {
		m.setId(no);
		no++;
		m.setRegDate(Util.getCurrentDate());

		members.add(m);
	}

	public Member getMemberById(int id) {
		for (int i = 0; i < members.size(); i++) {
			Member m = members.get(i);
			if (m.getId() == id) {
				return m;
			}
		}
		return null;
	}

	public Member getMemberByLoginIdAndLoginPw(String id, String pw) {

		for (int i = 0; i < members.size(); i++) {
			Member m = members.get(i);
			if (m.getLoginId().equals(id) && m.getLoginPw().equals(pw)) {
				return m;
			}
		}

		return null;
	}
}