package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import domain.User;
/**
 * 	可以通过spring构造方法注入这个属性
	private SqlSessionFactory sqlSessionFactory;
	public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	但这里通过继承SqlSessionDaoSupport
	查看它的源码可以发现有setSqlSessionFactory()方法，所以，这里是通过set注入
 */
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

	@Override
	public User findUserById(Integer id) {
		//通过继承的方法得到sqlSession
		SqlSession sqlSession = this.getSqlSession();
		User user = sqlSession.selectOne("test.findUserById", id);
		return user;
	}
	
}
