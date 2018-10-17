package cn.webrelax.bean;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.webrelax.entity.Bean2;
import cn.webrelax.entity.Bean3;
import cn.webrelax.entity.User;

/**
 *  先配置好
 *  <bean id="user" class="cn.webrelax.entity.User"></bean>
 *  
 *	bean实例化的三种方式：
 *	第一种 使用类的无参数构造创建（重点）
 *  第二种 使用静态工厂创建
 *  	（1）创建静态的方法，返回类对象
 *  第三种 使用实例工厂创建
 *   	（1）创建不是静态的方法，返回类对象
 */
public class TestIOC {
	@Test
	public void testUser() {
		//1. 加载spring配置文件
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2 得到配置创建的对象 参数为配置文件中的id属性
		//User类需要有无参构造函数
		User bean = (User)context.getBean("user");
		bean.add();
		
		//验证单例/多例：scope属性的值singleton/prototype
		User bean2=(User)context.getBean("user");
		System.out.println(bean+" "+bean2);
	}
	@Test
	public void testBean2() {
		//1. 加载spring配置文件
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2 得到配置创建的对象 参数为配置文件中的id属性
		//User类需要有无参构造函数
		Bean2 bean2 = (Bean2)context.getBean("bean2");;
		bean2.desc();
	}
	@Test
	public void testBean3() {
		//1. 加载spring配置文件
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2 得到配置创建的对象 参数为配置文件中的id属性
		Bean3 bean3 = (Bean3) context.getBean("bean3");
		bean3.desc();
	}
}
/**
 * IOC原理：
 * public class UserService{
 * 
 * }
 * piblic class UserServlet{
 * 		得到UserServlet的对象：
 * 		1、原始：new创建
 * 		2、工厂：UserFactory.getService();
 * }
 * 
 * 第一步：创建XML配置文件，配置要创建的对象类
 * 如：<bean id="userService" class="cn.webrelax.entity.UserService"></bean>
 * 第二步：创建工程类，使用dom4j解析配置文件+反射实现工厂模式
 * //返回UserService对象
 * public static UserService getService(){
 * 		1.用dom4j解析xml文件
 * 		根据id值userService,得到对应的class属性值
 * 		String classValue="class属性值"；
 * 		2.使用反射创建类对象
 * 		Class clazz=Class.forName(classValue);
 *		UserService service = clazz.newInstance();
 *		return service;	
 * }
 * */
