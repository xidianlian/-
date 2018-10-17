package cn.webrelax.data;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.webrelax.entity.Book;
import cn.webrelax.entity.User;
/**
 * 表达式封装（会用）
 * 1 实现过程
 *（1）使用表达式封装可以把表单数据封装到实体类对象里面
 * 第一步 在action里面声明实体类
 * 第二步 生成实体类变量的set和get方法
 * 第三步 在表单输入项的name属性值里面写表达式形式（如"user.username"）
 * 
 * 比较表达式封装和驱动封装
 * 1 使用表达式封装和模型驱动封装都可以把数据封装到实体类对象里面
 * 2 不同点：
 *（1）使用模型驱动只能把数据封装到一个实体类对象里面
 *  在一个action里面不能使用模型驱动把数据封装到不同的实体类对象里面
 *（2）使用表达式封装可以把数据封装到不同的实体类对象里面
 */
public class DataAction4 extends ActionSupport {
	private User user;//声明即可
	private Book book;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String execute() {
		System.out.println(user);
		System.out.println(book);
		return NONE;
	}
}
