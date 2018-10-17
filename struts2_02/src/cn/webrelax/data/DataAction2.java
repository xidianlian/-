package cn.webrelax.data;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.webrelax.entity.User;
/**
 *属性封装（了解）
 *1. 直接把表单提交属性封装到action的属性里面
 *2. 实现步骤
 *（1）在action成员变量位置定义变量
 * 变量名称和表单输入项的name属性值一样
 *（2）生成变量的set方法（把set和get方法都写出来）
 *3. 使用属性封装获取表单数据到属性里面，不能把数据直接封装到实体类对象里面
 */
public class DataAction2 extends ActionSupport {
	private String username;
	private String password;
	private String address;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String execute() {
		System.out.println(username+" "+password+" "+address);
		return NONE;
	}
}
