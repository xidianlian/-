package cn.webrelax.entity;

public class Book {

	private String bname;

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	@Override
	public String toString() {
		return "Book [bname=" + bname + "]";
	}
	
}
