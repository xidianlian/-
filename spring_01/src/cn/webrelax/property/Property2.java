package cn.webrelax.property;
/**
 *属性注入，方式2：
 *set 方式
 *
 */
public class Property2 {
	private String bookName;

	public void setBookName1(String bookName) {
		this.bookName = bookName;
	}
	public void test() {
		System.out.println("bookName:"+bookName);
	}
}
