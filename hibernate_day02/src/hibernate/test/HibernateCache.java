package hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.entity.User;
import hibernate.util.HibernateUtil;

public class HibernateCache {
	//验证一级缓存的存在
	@Test
	public void cache() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction bt = session.beginTransaction();
		//根据id,查询两次
		//第一步执行get方法之后，发送sql语句查询数据库
		//第二个执行get方法之后，没有发送sql语句，查询一级缓存内容
		/*
		hibernate的一级缓存
		（1）hibernate的一级缓存默认打开的
		（2）hibernate的一级缓存使用范围，是session范围，从session创建到session关闭范围
		（3）hibernate的一级缓存中，存储数据必须 持久态数据
		*/
		/**
		 * 原理：
		 * 把持久态的user放到一级缓存区和快照区（副本）。当user修改后，一级缓存区内容改变，快照区不变。
		 * 当提交事务的时候，对比两个区，如果内容不同，则更新数据库
		 */
		User user1=session.get(User.class, 2);
		System.out.println(user1.getUsername());
		
		User user2=session.get(User.class, 2);
		System.out.println(user2.getUsername());
		
		// 特性：持久态自动更新数据库
		user2.setAddress("中国");
		
		
		bt.commit();
		session.close();
		sessionFactory.close();
	}
}
