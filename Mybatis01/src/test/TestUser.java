package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import domain.User;

public class TestUser {
	@Test
	public void testFindUserById() throws Exception {
		
		String resource ="SqlMapConfig.xml";
		//通过流将核心配置文件读取进来
		InputStream inputStream = Resources.getResourceAsStream(resource);
 		//通过核心配置文件输入流来创建会话工厂
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		//通过工厂创建会话
		SqlSession openSession = factory.openSession();
		//第一个参数是查出配置文件里的sql   parameter=namespace+id
		User user=openSession.selectOne("test.findUserById", 1);
		System.out.println(user);
		openSession.close();
	}
	@Test
	public void testFindUserByName() throws IOException
	{

		String resource ="SqlMapConfig.xml";
		//通过流将核心配置文件读取进来
		InputStream inputStream = Resources.getResourceAsStream(resource);
 		//通过核心配置文件输入流来创建会话工厂
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		//通过工厂创建会话
		SqlSession openSession = factory.openSession();
		
		List<User> selectList = openSession.selectList("test.findUserByName", "张");
		
		for(User u:selectList) {
			System.out.println(u);
		}
		openSession.close();
	}
	
	@Test
	public void testInsertUser() throws IOException {
		String resource ="SqlMapConfig.xml";
		//通过流将核心配置文件读取进来
		InputStream inputStream = Resources.getResourceAsStream(resource);
 		//通过核心配置文件输入流来创建会话工厂
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		//通过工厂创建会话
		SqlSession openSession = factory.openSession();
		
		User user=new User();
		user.setUsername("weicheng");
		user.setBirthday(new Date());
		user.setAddress("西安雁塔区");
		user.setSex("男");
		openSession.insert("test.insertUser", user);
		//提交事务
		openSession.commit();
		
		System.out.println("返回的id："+user.getId());
		
		openSession.close();
	}
	@Test
	public void testDeleteById() throws IOException {
		String resource ="SqlMapConfig.xml";
		//通过流将核心配置文件读取进来
		InputStream inputStream = Resources.getResourceAsStream(resource);
 		//通过核心配置文件输入流来创建会话工厂
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		//通过工厂创建会话
		SqlSession openSession = factory.openSession();
		
		openSession.delete("test.delUserById", 29);
		
		//提交事务
		openSession.commit();
		
		openSession.close();
	}
	@Test
	public void testUpdateById() throws IOException {
		String resource ="SqlMapConfig.xml";
		//通过流将核心配置文件读取进来
		InputStream inputStream = Resources.getResourceAsStream(resource);
 		//通过核心配置文件输入流来创建会话工厂
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		//通过工厂创建会话
		SqlSession openSession = factory.openSession();
		
		User user=new User();
		user.setId(29);
		user.setUsername("汤普森");
		
		openSession.update("test.updateUserById", user);
		
		//提交事务
		openSession.commit();
		
		openSession.close();
	}
}
