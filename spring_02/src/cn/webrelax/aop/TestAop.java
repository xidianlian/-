package cn.webrelax.aop;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAop {
	@Test
	public void testAop() {
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("aop.xml");
		Book book1 = (Book) context.getBean("book1");
		book1.add();
	}
}
