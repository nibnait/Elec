package junit;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.dcfun.elec.domain.ElecSystemDDL;


public class TestHibernateCache {

	/**测试Session的一级缓存*/
	@Test
	public void testCache(){
		Configuration configuration = new Configuration();
		configuration.configure();//加载classpath下的hibernate.cfg.xml的文件
		SessionFactory sf = configuration.buildSessionFactory();
		Session s = sf.openSession();
		Transaction tr = s.beginTransaction();
		
		ElecSystemDDL ddl1 = (ElecSystemDDL) s.get(ElecSystemDDL.class, 1);//产生select语句
		ElecSystemDDL ddl2 = (ElecSystemDDL) s.get(ElecSystemDDL.class, 1);//不产生select语句，从一级缓存中查询
		
		tr.commit();
		s.close();
		/****************************************************/
		s = sf.openSession();
		tr = s.beginTransaction();
		
		ElecSystemDDL ddl3 = (ElecSystemDDL) s.get(ElecSystemDDL.class, 1);//产生select语句
		
		tr.commit();
		s.close();
	}
	
	/**测试Query的查询缓存*/
	@Test
	public void testQueryCache(){
		Configuration configuration = new Configuration();
		configuration.configure();//加载classpath下的hibernate.cfg.xml的文件
		SessionFactory sf = configuration.buildSessionFactory();
		Session s = sf.openSession();
		Transaction tr = s.beginTransaction();
		
		Query query = s.createQuery("from ElecSystemDDL o where o.keyword='性别'");
		//5：查询该语句，启用查询缓存
		query.setCacheable(true);
		query.list();//产生select语句
		
		tr.commit();
		s.close();
		/****************************************************/
		s = sf.openSession();
		tr = s.beginTransaction();
		
		Query query1 = s.createQuery("from ElecSystemDDL o where o.keyword='性别'");
		//5：查询该语句，启用查询缓存
		query1.setCacheable(true);
		query1.list();//也产生select语句
		
		tr.commit();
		s.close();
	}
}
