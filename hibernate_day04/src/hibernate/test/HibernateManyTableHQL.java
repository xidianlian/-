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
 *HQL 多表查询
 *
 *Mysql语句：
 *内连接：只查询匹配的连接行
 *select * from Customer as c,LinkMan as l where c.cid=l.clid;
 *select * from Customer as c INNER JOIN LinkMan as l ON c.cid=l.clid;
 *左外连接：包含左表的全部行
 *select * from Customer as c LEFT JOIN LinkMan as l on c.cid=l.clid;
 *右外连接：包含右表的全部行
 *select * from Customer as c RIGHT JOIN LinkMan as l on c.cid=l.clid;
 */
public class HibernateManyTableHQL {
	/**
	 * 内连接&迫切内连接
	 */
	@Test
	public void testInnerJoin() {
		Session session=null;
		Transaction bs=null;
		try {
			session = HibernateUtil.getSessionObject();
			bs = session.beginTransaction();
			//setLinkMan 是 Customer中的属性
			Query query = session.createQuery("from Customer c inner join c.setLinkMan");
			List<Object[]> list = query.list();
		    for(Object[] o:list) {
		    	System.out.println(Arrays.toString(o));
		    }
		    //迫切内连接
//		    （1）迫切内连接和内连接底层实现一样的
//		    （2）区别：使用内连接返回list中每部分是数组，迫切内连接返回list每部分是对象
//		    （3）hql语句写法
//		    - from  Customer  c  inner  join  fetch  c.setLinkMan
		    Query query2 = session.createQuery("from Customer c inner join fetch c.setLinkMan");
		    List<Customer> list2 = query2.list();
		    //...(这里就不输出了)
			bs.commit();
		}catch(Exception e) {
			e.printStackTrace();
			bs.rollback();
		}finally {
			
		}
	}
	/**
	 * 左外链接&迫切左外连接
	 * 右外连接不再演示
	 */
	@Test
	public void testLeftJoin() {
		Session session=null;
		Transaction bs=null;
		try {
			session = HibernateUtil.getSessionObject();
			bs = session.beginTransaction();
			//setLinkMan 是 Customer中的属性,关键字：left outer join
			Query query = session.createQuery("from Customer c left outer join c.setLinkMan");
			List<Object[]> list = query.list();
		    for(Object[] o:list) {
		    	System.out.println(Arrays.toString(o));
		    }
		    //同理，迫切左外连接加关键字 fetch
		    //语句为：from Customer c left outer join fetch c.setLinkMan
		    
		    
		    //同理，右外连接只需修改left为right
			bs.commit();
		}catch(Exception e) {
			e.printStackTrace();
			bs.rollback();
		}finally {
			
		}
	}
}
