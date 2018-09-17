package hibernate.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import hibernate.entity.Customer;
import hibernate.entity.LinkMan;
import hibernate.entity.People;
import hibernate.entity.Role;
import hibernate.util.HibernateUtil;

/**
 * 
 * QBC(query by criteria)
 *
 */
public class HibernateQBC {
	/**
	 * 查询所有
	 */
	@Test
	public void testSelectAll() {
		Session session=null;
		Transaction bs=null;
		try {
			session = HibernateUtil.getSessionObject();
			bs = session.beginTransaction();
			Criteria criteria = session.createCriteria(Customer.class);
			
			List<Customer> list = criteria.list();
			for(Customer c:list) {
				System.out.println(c);
			}
			
			bs.commit();
		}catch(Exception e) {
			e.printStackTrace();
			bs.rollback();
		}finally {
			
		}
	}
	/**
	 * 条件查询,where、like、order、limit
	 */
	@Test
	public void testSelectWhere() {
		Session session=null;
		Transaction bs=null;
		try {
			session = HibernateUtil.getSessionObject();
			bs = session.beginTransaction();
			Criteria criteria = session.createCriteria(Customer.class);
			//add添加条件
			criteria.add(Restrictions.eq("cid", 2));
			criteria.add(Restrictions.like("custName", "%阿里%"));
			List<Customer> list = criteria.list();
			for(Customer c:list) {
				System.out.println(c);
			}
			
			//排序查询
			Criteria criteria2 = session.createCriteria(People.class);
			criteria2.addOrder(Order.asc("pid"));
			List<People>list2=criteria2.list();
			for(People p:list2) {
				System.out.println(p);
			}
			
			//分页查询
			Criteria criteria3 = session.createCriteria(Role.class);
			criteria3.setFirstResult(0);
			criteria3.setMaxResults(2);
			List<Role>list3=criteria3.list();
			for(Role r:list3) {
				System.out.println(r);
			}
			bs.commit();
		}catch(Exception e) {
			e.printStackTrace();
			bs.rollback();
		}finally {
			
		}
	}
	/**
	 * 统计查询 count
	 */
	@Test
	public void testSelectCount() {
		Session session=null;
		Transaction bs=null;
		try {
			session = HibernateUtil.getSessionObject();
			bs = session.beginTransaction();
			Criteria criteria = session.createCriteria(Customer.class);
			criteria.setProjection(Projections.rowCount());
			Object result = criteria.uniqueResult();
			
			Long count=(Long)result;
			System.out.println(count.intValue());
			bs.commit();
		}catch(Exception e) {
			e.printStackTrace();
			bs.rollback();
		}finally {
			
		}
	}
}
