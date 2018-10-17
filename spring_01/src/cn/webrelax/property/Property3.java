package cn.webrelax.property;

import cn.webrelax.entity.User;

/**
 *用set方法注入对象 
 *
 */
public class Property3 {
	private User user1;

	public void setUser1(User user1) {
		this.user1 = user1;
	}
	public void test() {
		System.out.println(user1);
	}
}
