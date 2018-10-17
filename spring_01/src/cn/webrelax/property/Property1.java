package cn.webrelax.property;

import org.junit.Test;

/**
 * 这个包里面，介绍属性注入(说白了就是给类里面的属性赋值)
 *1.属性注入的方式介绍（三种方式）
 *（1）使用set方法注入
 *（2）使用有参数构造注入
 *（3）使用接口注入
 *2. 在spring框架里面，支持前两种方式
 *（1）有参数构造注入
 *（2）set方法注入（重点）
 *
 */
/**
 * （1）有参构造注入
 * 
 */
public class Property1 {
	private String username;
	
	public Property1(String username) {
		this.username=username;
	}
	
	public void test() {
		System.out.println(username);
	}
}
