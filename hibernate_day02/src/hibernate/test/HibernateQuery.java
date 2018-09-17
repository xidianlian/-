package hibernate.test;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.entity.User;
import hibernate.util.HibernateUtil;

public class HibernateQuery {
	@Test
	public void testQuery() {
		SessionFactory sessionFactory=null;
		Session session=null;
		Transaction ts=null;
		try {
			sessionFactory=HibernateUtil.getSessionFactory();
			session=HibernateUtil.getSessionObject();
			ts=session.beginTransaction();
			
			//方法里面写hql(hibernate query language)
			Query query=session.createQuery("from User");
			List<User> list =query.list();
			for(User user:list) {
				System.out.println(user);
			}
			
			ts.commit();
		}catch(Exception e){
			e.printStackTrace();
			ts.rollback();
		}finally {
			
		}
	}
	
	@Test
	public void testCriteria() {
		SessionFactory sessionFactory=null;
		Session session=null;
		Transaction ts=null;
		try {
			sessionFactory=HibernateUtil.getSessionFactory();
			session=HibernateUtil.getSessionObject();
			ts=session.beginTransaction();
			
			Criteria creiteria=session.createCriteria(User.class);
			List<User>list=creiteria.list();
			for(User user:list) {
				System.out.println(user);
			}
			
			ts.commit();
		}catch(Exception e){
			e.printStackTrace();
			ts.rollback();
		}finally {
			
		}
	}
	@Test
	public void testSQLQuery() {
		SessionFactory sessionFactory=null;
		Session session=null;
		Transaction ts=null;
		try {
			sessionFactory=HibernateUtil.getSessionFactory();
			session=HibernateUtil.getSessionObject();
			ts=session.beginTransaction();
			
			SQLQuery sqlquery=session.createSQLQuery("select * from user");
//			//返回的是数组结构
//			List<Object[]> list = sqlquery.list();
//			for(Object[] objects:list) {
//				System.out.println(Arrays.toString(objects));
//			}
			
			//返回list中每部分的对象形式
			sqlquery.addEntity(User.class);
			List<User>list2=sqlquery.list();
			for(User user:list2) {
				System.out.println(user);
			}
			
			ts.commit();
		}catch(Exception e){
			e.printStackTrace();
			ts.rollback();
		}finally {
			
		}
	}
}
