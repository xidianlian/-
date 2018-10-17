package cn.webrelax.data;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.webrelax.entity.User;
/**
 *原始方式获取表单封装到实体类对象 
 *
 */
public class DataAction1 extends ActionSupport {
	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		User user =new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setAddress(address);
		System.out.println(user);
		return NONE;
	}
}
