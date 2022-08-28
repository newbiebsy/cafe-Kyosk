package vo;

public class CafeMemberVO {
	String id, name, pw;
	int stamp;

	public CafeMemberVO() {
	}

	public CafeMemberVO(String id, String name, String pw, int stmap) {
		super();
		this.id = id;
		this.name = name;
		this.pw = pw;
		this.stamp = stmap;
	}

	public CafeMemberVO(String id, String name, String pw) {
		super();
		this.id = id;
		this.name = name;
		this.pw = pw;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getStamp() {
		return stamp;
	}

	public void setStamp(int stmap) {
		this.stamp = stamp;
	}

}
	