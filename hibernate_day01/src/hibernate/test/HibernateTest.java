package hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import hibernate.entity.User;

public class HibernateTest {
	
	@Test
	public void testAdd() {
//		第一步 加载hibernate核心配置文件
		//到src下面找到名称hibernate.cfg.xml配置文件，创建对象，
		//把配置文件放到对象里面（加载核心配置文件）
		Configuration cfg=new Configuration();
		cfg.configure();
		
//		第二步 创建SessionFactory对象
		//读取hibernate核心配置文件内容，创建sessionFactory
		//1.创建sessionfactory过程中做事情：
		//根据核心配置文件中，有数据库配置，有映射文件部分，到数据库里面根据映射关系把表创建
		//<property name="hibernate.hbm2ddl.auto">update</property>
		//2.创建sessionFactory过程中，这个过程特别耗资源的
		//在hibernate操作中，建议一个项目一般创建一个sessionFactory对象
		//3 具体实现
		//写工具类，写静态代码块实现（HibernateUtil.java）
		//* 静态代码块在类加载时候执行，执行一次
		SessionFactory sessionFactory = cfg.buildSessionFactory();
//		第三步 使用SessionFactory创建session对象
		//1. session类似于jdbc中connection
		//2.调用session里面不同的方法实现crud操作
		//（1）添加 save方法
		//（2）修改 update方法
		//（3）删除 delete方法
		//（4）根据id查询 get方法
		//3.session对象单线程对象
		//（1）session对象不能共用，只能自己使用
		Session session = sessionFactory.openSession();
		
//		第四步 开启事务
		//（1）事务四个特性
		//原子性、
		//一致性、
		//隔离性、
		//持久性

		Transaction tx = session.beginTransaction();
		
//		第五步 写具体逻辑 crud操作
		User user=new User();
		user.setUsername("小王吧");
		user.setPassword("250");
		user.setAddress("美国");
		
		session.save(user);
//		第六步 提交事务
		tx.commit();
		//可以回滚事物
		//tx.rollback();
//		第七步 关闭资源
		session.close();
		sessionFactory.close();

	}
}
