package main.Data.hibernateHelper;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateHelper {

	private static SessionFactory factory;
	
	public static void initHibernateHelper(){
		try {
			factory = new Configuration().configure().buildSessionFactory();
			System.out.println("���ݿ����ӳɹ�");
		} catch (Throwable ex) { 
	    	System.err.println("Failed to create sessionFactory object." + ex);
	    	throw new ExceptionInInitializerError(ex); 
	    }
	}
	
	public static Session getSession(){
		return factory.openSession();
	}
}
