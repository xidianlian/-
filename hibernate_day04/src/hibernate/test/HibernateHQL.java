package hibernate.test;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.entity.Customer;
import hibernate.entity.LinkMan;
import hibernate.util.HibernateUtil;

/**
 *HQL(hibernate query language) 
 *
 */
public class HibernateHQL {
	/**
	 * 查询所有,排序
	 */
	@Test
	public void testQueryAll() {
		Session session=null;
		Transaction bs=null;
		try {
			session = HibernateUtil.getSessionObject();
			bs = session.beginTransaction();
			//from + 实体类名
			Query query = session.createQuery("from Customer");
			List<Customer> list = query.list();
			for(Customer c:list) {
				System.out.println(c);
			}
			Query query2 = session.createQuery("from LinkMan order by lkm_id desc");
			List<LinkMan> list2 = query2.list();
			for(LinkMan c:list2) {
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
	 * 条件查询、模糊查询
	 */
	@Test
	public void testQueryWhere() {
		Session session=null;
		Transaction bs=null;
		try {
			session = HibernateUtil.getSessionObject();
			bs = session.beginTransaction();
			//as c给表起别名，as可省略
			//select * from Customer as c where c.cid=? and c.custName=? ;
			Query query = session.createQuery("from Customer c where c.cid=? and c.custName=?");
			//设置参数，参数位置下表从0开始
			query.setParameter(0, 4);
			query.setParameter(1,"腾讯");
			List<Customer> list = query.list();
			for(Customer c:list) {
				System.out.println(c);
			}
			//模糊查询
			Query query2 = session.createQuery("from Customer where custName like ?");
			query2.setParameter(0, "%阿里%");
			list=query2.list();
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
	 * 分页
	 */
	@Test
	public void testQueryLimit() {
		Session session=null;
		Transaction bs=null;
		try {
			session = HibernateUtil.getSessionObject();
			bs = session.beginTransaction();
			//as c给表起别名，as可省略
			//select * from Customer as c limit 0,3 ;
			Query query = session.createQuery("from Customer");
			//设置开始数据
			query.setFirstResult(0);
			//设置数据个数
			query.setMaxResults(3);
			List<Customer> list = query.list();
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
	 * 投影查询，查某一些字段
	 */
	@Test
	public void testQueryColumn() {
		Session session=null;
		Transaction bs=null;
		try {
			session = HibernateUtil.getSessionObject();
			bs = session.beginTransaction();
			//as c给表起别名，as可省略
			//select custName,custLevel from Customer;
			Query query = session.createQuery("select custName,custLevel from Customer");
			List<Object[]> list = query.list();
			for(Object[] c:list) {
				System.out.println(Arrays.toString(c));
			}
			bs.commit();
		}catch(Exception e) {
			e.printStackTrace();
			bs.rollback();
		}finally {
			
		}
	}
	/**
	 * 聚合函数count、sum、avg、max、min
	 */
	@Test
	public void testQueryFunction() {
		Session session=null;
		Transaction bs=null;
		try {
			session = HibernateUtil.getSessionObject();
			bs = session.beginTransaction();
			
			Query query = session.createQuery("select count(*) from Customer");
			Object object = query.uniqueResult();
			System.out.println(object);
			bs.commit();
		}catch(Exception e) {
			e.printStackTrace();
			bs.rollback();
		}finally {
			
		}
	}
}
