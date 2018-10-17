package cn.webrelax.data;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.webrelax.entity.User;
/**
 * 1 使用模型驱动方式，可以直接把表单数据封装到实体类对象里面
 *
 * 2 实现步骤
 *（1）action实现接口 ModelDriven
 *（2）实现接口里面的方法 getModel方法 把创建对象返回
 *（3）在action里面创建实体类对象
 * 3 使用模型驱动和属性封装注意问题：
 *（1）在一个action中，获取表单数据可以属性封装，使用模型驱动封装，
 * 不能同时使用属性封装和模型驱动封装获取同一个表单数据
 * 如果同时使用，之后执行模型驱动
 */
public class DataAction3 extends ActionSupport implements ModelDriven<User>{
	private User user=new User();
	@Override
	public User getModel() {
		return user;
	}
	
	public String execute() {
		System.out.println(user);
		return NONE;
	}
}
