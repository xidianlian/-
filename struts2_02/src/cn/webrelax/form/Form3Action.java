package cn.webrelax.form;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 获取表单数据方式三 
 * 使用接口注入（了解）
 * 实现ServletRequestAware(为了得到request对象)
 */
public class Form3Action extends ActionSupport implements ServletRequestAware{
	HttpServletRequest request=null;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

	public String execute() {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		System.out.println(username+" "+password+" "+address);
		return NONE;
	}
}
