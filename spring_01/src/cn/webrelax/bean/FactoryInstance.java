package cn.webrelax.bean;

import cn.webrelax.entity.Bean3;

/**
 * 第三种 使用实例工厂创建
 *（1）创建不是静态的方法，返回类对象
 *
 */
public class FactoryInstance {
	//普通方法，返回bean3对象
	public Bean3 getBean3() {
		return new Bean3();
	}
}
