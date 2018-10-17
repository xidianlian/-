package cn.webrelax.annotation;

import org.springframework.stereotype.Component;

@Component(value="ud")
public class UserDao {
	public void addUser() {
		System.out.println("adduser...");
	}
}
