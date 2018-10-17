package cn.webrelax.data;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import cn.webrelax.entity.User;

/**
 * 封装数据到List集合
 * 第一步 在action声明List
 * 第二步 生成list变量的set和get方法
 * 第三步 在表单输入项里面写表达式(如"list[0].username")
 */
public class ListAction extends ActionSupport {
	private List<User>list;
	
	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

	public String execute() {
		for(User l:list) {
			System.out.println(l);
		}
		return NONE;
	}
}
