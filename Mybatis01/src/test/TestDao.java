package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import dao.UserDao;
import dao.UserDaoImpl;
import domain.User;

public class TestDao {
	private SqlSessionFactory factory;
	//junit注解，在测试方法前执行这个方法
	@Before
	public void setUp() throws IOException {
		String resource ="SqlMapConfig.xml";
		//通过流将核心配置文件读取进来
		InputStream inputStream = Resources.getResourceAsStream(resource);
 		//通过核心配置文件输入流来创建会话工厂
		factory = new SqlSessionFactoryBuilder().build(inputStream);
		//通过工厂创建会话
	}
	@Test
	public void testFindUserById() {
		//模拟通过框架注入属性
		UserDao userDao=new UserDaoImpl(factory);
		User user = userDao.findUserById(10);
		System.out.println(user);
	}
	@Test 
	public void testFindUserByName() {
		UserDao userDao=new UserDaoImpl(factory);
		 List<User> list = userDao.findUserByName("小明");
		 for(User u:list) {
			 System.out.println(u);
		 }
	}
	
	
	
	
	
}
