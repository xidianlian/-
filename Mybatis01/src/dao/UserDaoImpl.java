package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import domain.User;

public class UserDaoImpl implements UserDao {

	//通过构造方法注入这个属性
	private SqlSessionFactory sqlSessionFactory;
	
	
	public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public User findUserById(Integer id) {
		//sqlSession是线程不安全的，所以它的最佳使用范围在方法体内
		//即如果将它作为类属性，那么如果一个线程修改数据，一个线程查询数据，就会出现数据紊乱
		SqlSession openSession = sqlSessionFactory.openSession();
		User user = openSession.selectOne("test.findUserById", id);
		return user;
	}

	@Override
	public List<User> findUserByName(String userName) {
		SqlSession openSession = sqlSessionFactory.openSession();
		List<User> selectList = openSession.selectList("test.findUserByName", userName);
		return selectList;
	}

	
	
}
