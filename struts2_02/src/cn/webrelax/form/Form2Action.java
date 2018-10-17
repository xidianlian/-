package cn.webrelax.form;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 获取表单数据方式二
 * 通过ServletActionContext获取request再得到参数
 * 这和Servlet一样
 */
public class Form2Action extends ActionSupport{
	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		//ServletActionContext可操作域对象
//	    HttpSession session = request.getSession();
//	    session.setAttribute("ss", "ok");
//	    ServletContext servletContext = ServletActionContext.getServletContext();
//	    servletContext.setAttribute("a", "are you");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		System.out.println(username+" "+password+" "+address);
		return NONE;
	}
}
