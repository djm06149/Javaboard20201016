public class Like {

	private int id; //  ���ƿ� ��ȣ
	private int parentId; //���� ��ȣ
	private int memberId; // üũ ���� ��ȣ
	private String regDate; // ��ϳ�¥

	public Like() {

	}

	public Like(int parentId, int memberId) {
		this.parentId = parentId;
		this.memberId = memberId;

	}

	public Like(int parentId, int memberId, String regDate) {
		this.parentId = parentId;
		this.memberId = memberId;
		this.regDate = regDate;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

}