package cn.webrelax.bean;

import cn.webrelax.entity.Bean2;

/**
 * 第二种 使用静态工厂创建
 *  （1）创建静态的方法，返回类对象
 *
 */
public class StaticFactory {
	public static Bean2 getBean2() {
		return new Bean2();
	}
}	
