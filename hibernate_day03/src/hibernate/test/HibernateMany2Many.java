package hibernate.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.entity.People;
import hibernate.entity.Role;
import hibernate.util.HibernateUtil;

public class HibernateMany2Many {
	/**
	 * 级联保存  
	 */
	@Test
	public void testSave() {
	Transaction ts=null;
		
		try {
			Session session = HibernateUtil.getSessionObject();
			ts = session.beginTransaction();
			
			People p1=new People();
			p1.setName("库里1");
			People p2=new People();
			p2.setName("马云1");
			
			Role r1=new Role();
			r1.setProfession("运动员");
			Role r2=new Role();
			r2.setProfession("商人");
			
			p1.getRoles().add(r1);
			p1.getRoles().add(r2);//库里既是球员又是商人
			p2.getRoles().add(r2);
			
			//再People.hbm.xml设置了 <set cascade="save-update">
			session.save(p1);
			session.save(p2);
		
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
			
			People p1=session.get(People.class, 5);
			session.delete(p1);
			
			ts.commit();
		}catch(Exception e){
			ts.rollback();
			e.printStackTrace();
		}finally {
			
		}
	}
	/**
	 * 维护第三张表,给某人增加角色
	 */
	@Test
	public void testRelationTable1() {
		Transaction ts=null;
		
		try {
			Session session = HibernateUtil.getSessionObject();
			ts = session.beginTransaction();
			
			People p1=session.get(People.class, 8);
			Role r1=session.get(Role.class,6);
			p1.getRoles().add(r1);
			
			ts.commit();
		}catch(Exception e){
			ts.rollback();
			e.printStackTrace();
		}finally {
			
		}
	}
	/**
	 * 维护第三张表,给某人去掉某角色
	 */
	@Test
	public void testRelationTable2() {
		Transaction ts=null;
		
		try {
			Session session = HibernateUtil.getSessionObject();
			ts = session.beginTransaction();
			
			People p1=session.get(People.class, 8);
			Role r1=session.get(Role.class,6);
			p1.getRoles().remove(r1);
			
			ts.commit();
		}catch(Exception e){
			ts.rollback();
			e.printStackTrace();
		}finally {
			
		}
	}
}
