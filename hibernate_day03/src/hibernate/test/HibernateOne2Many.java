package hibernate.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.entity.Customer;
import hibernate.entity.LinkMan;
import hibernate.util.HibernateUtil;

/**
 *一对多测试
 */
public class HibernateOne2Many {
	@Test
	public void testSave() {
		Transaction ts=null;
		
		try {
			Session session = HibernateUtil.getSessionObject();
			ts = session.beginTransaction();
			// 添加一个客户，为这个客户添加一个联系人
			//1 创建客户和联系人对象

			Customer cust=new Customer();
			cust.setCustName("阿里巴巴");
			cust.setCustLevel("vip");
			cust.setCustMobile("110");
			cust.setCustSource("null");
			
			LinkMan lm=new LinkMan();
			lm.setLkm_name("马云");
			lm.setLkm_gender("男");
			lm.setLkm_phone("1144564");
			
			//2 在客户表示所有联系人，在联系人表示客户		
			// 建立客户对象和联系人对象关系
			//2.1 把联系人对象 放到客户对象的set集合里面
			cust.getSetLinkMan().add(lm);
			//2.2 把客户对象放到联系人里面
			lm.setCustomer(cust);
			
			session.save(lm);
			session.save(cust);
			ts.commit();
		}catch(Exception e){
			ts.rollback();
			e.printStackTrace();
		}finally {
			
		}
	}
	/**
	 * 简化版，
	 * 1. 在客户映射文件里面set标签进行配置 <set cascade="save-update,delete">（inverse设为false）
	 * 2. 创建客户和联系人对象，只需要把联系人放到客户里面就可以了，最终只需要保存客户就可以了
	 */
	@Test
	public void testSave2() {
		Transaction ts=null;
		
		try {
			Session session = HibernateUtil.getSessionObject();
			ts = session.beginTransaction();
			
			Customer cust=new Customer();
			cust.setCustName("京东");
			cust.setCustLevel("vip");
			cust.setCustMobile("120");
			cust.setCustSource("null");
			
			LinkMan lm=new LinkMan();
			
			lm.setLkm_name("强东");
			lm.setLkm_gender("男");
			lm.setLkm_phone("0813-942227");
			//把联系人对象 放到客户对象的set集合里面
			cust.getSetLinkMan().add(lm);
			
			
			session.save(cust);
			ts.commit();
		}catch(Exception e){
			ts.rollback();
			e.printStackTrace();
		}finally {
			
		}
	}
	/**
	 * 级联删除
	 */
	@Test
	public void testDelete() {
		Transaction ts=null;
		
		try {
			Session session = HibernateUtil.getSessionObject();
			ts = session.beginTransaction();
			
			Customer cust=session.get(Customer.class, 1);
			session.delete(cust);
	
			ts.commit();
		}catch(Exception e){
			ts.rollback();
			e.printStackTrace();
		}finally {
			
		}
	}
	/**
	 * 修改LinkMan的记录
	 */
	@Test
	public void testUpdate() {
		Transaction ts=null;
		
		try {
			Session session = HibernateUtil.getSessionObject();
			ts = session.beginTransaction();
			//1 根据id查询联系人
			Customer cust=session.get(Customer.class, 2);
			LinkMan lm=session.get(LinkMan.class, 7);
			//2 设置持久态对象值
			//把联系人放到客户里面
			cust.getSetLinkMan().add(lm);
			//把客户放到联系人里面
			lm.setCustomer(cust);
	
			ts.commit();
		}catch(Exception e){
			ts.rollback();
			e.printStackTrace();
		}finally {
			
		}
		/**
		 * 运行结果可以看到sql语句，更新了两次LinkMan表：
		 * Hibernate: 
			    update
			        LinkMan 
			    set
			        lkm_name=?,
			        lkm_gender=?,
			        lkm_phone=?,
			        clid=? 
			    where
			        lkm_id=?
			Hibernate: 
			    update
			        LinkMan 
			    set
			        clid=? 
			    where
			        lkm_id=?
			 
			 原因：hibernate机制是双向维护外键，在一和多那一方都配置外键，造成性能不高。
			 解决：一对多里面，让其中一方放弃外键维护（一般是一的一方）
 				一个国家有总统，国家有很多人，总统不能认识国家所有人，国家所有人可以认识总统

			 实现：在customer配置文件中,inverse="true"，<set inverse="true">
		 */
	}
}
