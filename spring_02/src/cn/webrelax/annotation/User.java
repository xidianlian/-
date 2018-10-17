package cn.webrelax.annotation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 * Spring中提供@Component的三个衍生注解（功能目前来讲是一致的）
 * 	@Controller :web层
 * 	@service 	:业务层
 * 	@Repository :持久层
 * 
 * 目前这四个注解功能是一样的，都创建对象，只是Spring为了以后的拓展
 *
 */

@Component(value="user")//相当于之前在配置文件中写<bean id="user" calss="...">
@Scope(value="prototype")//单例和多例
public class User {
	public void userTest() {
		System.out.println("userTest....");
	}
	
}
