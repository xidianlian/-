package hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.entity.User;
import hibernate.util.HibernateUtil;

public class HibernateCRUD {
	
	/**
	 * 根据id查询数据
	 */
	@Test
	public void testGet() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		//根据id查询数据库
		User user = session.get(User.class, 2);
		System.out.println(user.getUsername());
//		 提交事务
		tx.commit();
		//可以回滚事物
		//tx.rollback();
//		 关闭资源
		session.close();
		sessionFactory.close();

	}
	@Test
	public void testUpdate() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		//修改操作：先查再改
		User user = session.get(User.class, 23);
		if(user!=null) {
			user.setUsername("alice");
			session.update(user);
		}
		
		tx.commit();
		
		session.close();
		sessionFactory.close();
	}
	@Test
	public void testDelete() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		//删除
		User user=new User();
		user.setUid(1);
		session.delete(user);
		
		tx.commit();
		
		session.close();
		sessionFactory.close();
	}
	@Test
	public void testSaveOrUpdate() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		//瞬时态：无id,与session无关联
		//持久态：有id,与session有关联
		//托管态：有id,与session无关联
		
//		//insert（瞬时态）
//		User user1=new User();
//		user1.setUsername("bob");
//		session.saveOrUpdate(user1);
//		
//		//update（持久态和托管态）
		User user2=new User();
		user2.setUid(2);//有id=2的记录更新，如果没有，则不修改
		user2.setUsername("库里");
		session.saveOrUpdate(user2);
		
		User user3=session.get(User.class, 3);
		user3.setUsername("汤普森");
		session.saveOrUpdate(user3);
		
		tx.commit();
		
		session.close();
		sessionFactory.close();
	}
}
