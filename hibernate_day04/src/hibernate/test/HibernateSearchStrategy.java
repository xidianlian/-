package hibernate.test;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.entity.Customer;
import hibernate.entity.LinkMan;
import hibernate.util.HibernateUtil;

/**
 * 检索策略
 * 
 * 1.hibernate检索策略分为两类：
 *（1）立即查询：根据id查询，调用get方法，一调用get方法马上发送语句查询数据库
 *（2）延迟查询：根据id查询，还有load方法，调用load方法不会马上发送语句查询数据，只有得到对象里面的值时候才会发送语句查询数据库
 *
 * 2.延迟查询分成两类：
 *（1）类级别延迟：根据id查询返回实体类对象，调用load方法不会马上发送语句
 *（2）关联级别延迟：查询某个客户，再查询这个客户的所有联系人,
 * 查询客户的所有联系人的过程是否需要延迟，这个过程称为关联级别延迟
 * 
 * 用单步调试观察每步执行输出mysql语句的过程
 */
public class HibernateSearchStrategy {
	/**
	 * 立即查询&延迟查询
	 */
	@Test
	public void testSelect1() {
		Session session=null;
		Transaction bs=null;
		try {
			session = HibernateUtil.getSessionObject();
			bs = session.beginTransaction();
			
			//根据cid=2客户
			//执行get方法之后，是否发送sql语句
			//调用get方法马上发送sql语句查询数据库
//			Customer customer = session.get(Customer.class, 2);
			
			/*
			 * 1 调用load方法之后，不会马上发送sql语句
			 * （1）返回对象里面只有 id值
			 * 
			 * 2 得到对象里面不是id的其他值时候才会发送语句
			 * */
			Customer customer2 = session.load(Customer.class, 2);
			System.out.println(customer2.getCid());//这个值直接从参数得到
			System.out.println(customer2.getCustName());//这里才发送mysql语句
			bs.commit();
		}catch(Exception e) {
			e.printStackTrace();
			bs.rollback();
		}finally {
			
		}
	}
	/**
	 *1.在映射文件中进行配置实现
	 *（1）根据客户得到所有的联系人，在客户映射文件中配置
	 *2.在set标签上使用属性
	 *（1）fetch：值select（默认）
	 *（2）lazy：值
	 *	  true：延迟（默认）
     *    false：不延迟
     *    extra：极其延迟
	 */
	@Test
	public void testSelect2() {
		Session session=null;
		Transaction bs=null;
		try {
			session = HibernateUtil.getSessionObject();
			bs = session.beginTransaction();
			
			//先根据id查客户，再查联系人
			Customer customer = session.load(Customer.class, 2);
			//得到set集合，没有发送语句
			Set<LinkMan> setLinkMan = customer.getSetLinkMan();
			//这里才发送语句
			System.out.println(setLinkMan.size());
			
			bs.commit();
		}catch(Exception e) {
			e.printStackTrace();
			bs.rollback();
		}finally {
			
		}
	}
	/**
	 * 批量抓取
	 * 1. 查询所有的客户，返回list集合，遍历list集合，得到每个客户，得到每个客户的所有联系人
	 *    这样会发送多条sql语句，效率不高
	 * 2. 在客户的映射文件中，set标签配置batch-size值，值越大发送语句越少
	 *
	 */
	@Test
	public void testSelect3() {
		Session session=null;
		Transaction bs=null;
		try {
			session = HibernateUtil.getSessionObject();
			bs = session.beginTransaction();
			
			//先根据id查客户，再查联系人
			Customer customer = session.load(Customer.class, 2);
			Set<LinkMan> setLinkMan = customer.getSetLinkMan();
 			for(LinkMan lm:setLinkMan) {
				System.out.println(lm.getLkm_name()+" "+lm.getLkm_phone());
			}
			
			bs.commit();
		}catch(Exception e) {
			e.printStackTrace();
			bs.rollback();
		}finally {
			
		}
	}
}
