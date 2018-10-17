package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import dao.UserDao;
import dao.UserDaoImpl;
import domain.User;
import mapper.UserMapper;

public class TestUserMapper {
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
		SqlSession openSession = factory.openSession();
		//通过getMapper方法来实例化接口
		UserMapper mapper = openSession.getMapper(UserMapper.class);
		User user = mapper.findUserById(10);
		System.out.println(user);
	}
	@Test 
	public void testFindUserByName() {
		SqlSession openSession = factory.openSession();
		//通过getMapper方法来实例化接口
		UserMapper mapper = openSession.getMapper(UserMapper.class);
		List<User> list = mapper.findUserByName("小明");
		for(User u:list) {
			 System.out.println(u);
		}
	}
	@Test
	public void testInsertUser() {
		SqlSession openSession = factory.openSession();
		UserMapper mapper = openSession.getMapper(UserMapper.class);
		User user=new User();
		user.setAddress("中国");
		user.setSex("女");
		user.setUsername("老王");
		user.setBirthday(new Date());
		mapper.insertUser(user);
		
		openSession.commit();
	}
}
