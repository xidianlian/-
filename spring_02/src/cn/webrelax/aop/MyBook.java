package cn.webrelax.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class MyBook {
	public void before1() {
		System.out.println("before1...");
	}
	public void after1() {
		System.out.println("after1...");
	}
	public void around1(ProceedingJoinPoint pj) throws Throwable {
		//
		System.out.println("方法之前");
		
		//执行被增强的方法
		pj.proceed();
		
		System.out.println("方法之后");
	}
}
