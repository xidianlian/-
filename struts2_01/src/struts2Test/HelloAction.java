package struts2Test;

public class HelloAction {
	/*
	 * （1）每次访问servlet时候，都会执行service方法
	 * - 写类继承HttpServlet，重写类 里面的方法
	 * - 在web.xml中配置servlet访问路径
	 * 
	 * （2）访问action，每次访问action时候，默认执行名称execute方法
	 * - 配置action访问路径
	 * */
	public String execute() {
		return "ok";
	}
	//也可以写其他方法，在配置文件<action>中的method方法进行配置
	public String add() {
		System.out.println("add...");
		return "none";
	}
	//在action里面的方法有返回值，如果有返回值时，类型必须是String
	//action里面的方法可以没有返回值，没有返回值时候，在result标签不需要配置
	// 把方法写成void
	//让返回值，返回 ”none”
	public void update() {
		System.out.println("update");
	}
	public String delete() {
		return "none";
	}
}
