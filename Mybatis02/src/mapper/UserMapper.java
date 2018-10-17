package mapper;

import java.util.List;

import domain.User;
/**
 *动态代理dao 
 *通过xml配置文件，不写实现类，自动生成
 *
 */
public interface UserMapper {

	public User findUserById(Integer id);
	
	//动态代理形式中,如果返回结果集问List,那么mybatis会在生成实现类的使用会自动调用selectList方法
	public List<User> findUserByName(String userName);
	
	public void insertUser(User user);
	
	public void updateUser(User user);
}
