package hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.entity.User;
import hibernate.util.HibernateUtil;

public class HibernateStandard {
	@Test
	public void standard() {
		SessionFactory sessionFactory=null;
		Session session=null;
		Transaction ts=null;
		try {
			sessionFactory=HibernateUtil.getSessionFactory();
			//session=sessionFactory.openSession();
			//与本地线程绑定的session
			session=HibernateUtil.getSessionObject();
			//开始事务
			ts=session.beginTransaction();
			
			User user =new User();
			user.setUsername("格林5");
			user.setPassword("42444223");
			user.setAddress("印度1");
			session.save(user);			
			//int i=10/0;
			
			ts.commit();
		}catch(Exception e){
			e.printStackTrace();
			ts.rollback();
		}finally {
			//session.close();
			//用本地线程绑定的session与线程共存，线程结束，session自动关闭
			
			//sessionFactory.close();
		}
	}
}
