package cn.webrelax.annotation;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="us")
public class UserService {
	
	//得到dao对象
	//1 定义dao类型属性
	// 使用注解方式时候不需要set方法
	@Autowired  //自动
	private UserDao userDao;
	
	//或者
	@Resource(name="ud")
	private UserDao ud;
	
	public void add() {
		System.out.println("service add...");
		userDao.addUser();
		ud.addUser();
	}
}
