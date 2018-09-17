package hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class HibernateUtil {
	private final static Configuration cfg;
	private final static SessionFactory sessionFactory;
	//静态代码块实现
	static {
		//加载核心配置文件
		cfg=new Configuration();
		cfg.configure();
		sessionFactory = cfg.buildSessionFactory();
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
