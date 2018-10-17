package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.UserDao;
import domain.User;

public class TestUserImpl {
	private ApplicationContext applicationContext;
	
	@Before
	public void setUp() {
		applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	}
	@Test
	public void testFindUserById() {
		UserDao userdao =(UserDao)applicationContext.getBean("userdao");
		User user = userdao.findUserById(1);
		System.out.println(user);
	}
	
}
