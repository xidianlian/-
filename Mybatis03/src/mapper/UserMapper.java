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
	

}
