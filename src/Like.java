
public class Like {
	
	private int id;
	private int parentId;
	private int likeFlag;
	private int checkMemberId;
	private String regDate;
	
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
	public int getLikeFlag() {
		return likeFlag;
	}
	public void setLikeFlag(int likeFlag) {
		this.likeFlag = likeFlag;
	}
	public int getCheckMemberId() {
		return checkMemberId;
	}
	public void setCheckMemberId(int checkMemberId) {
		this.checkMemberId = checkMemberId;
	}
}
