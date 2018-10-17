package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import domain.User;
import mapper.UserMapper;

public class TestUserMapper {
private ApplicationContext applicationContext;
	
	@Before
	public void setUp() {
		applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	}
	@Test
	public void testFindUserById() {
		UserMapper mapper = (UserMapper) applicationContext.getBean("userMapper");
		User user = mapper.findUserById(1);
		System.out.println(user);
	}
}
