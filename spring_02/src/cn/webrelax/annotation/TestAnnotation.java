package cn.webrelax.annotation;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAnnotation {
	@Test
	public void testUser() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
		User userBean =(User) context.getBean("user");
		userBean.userTest();
		
		//单例或多例
		User userBean1 =(User) context.getBean("user");
		System.out.println(userBean==userBean1);
	}
	@Test
	public void testService() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
		
		UserService us = (UserService)context.getBean("us");
		us.add();
	}
}
