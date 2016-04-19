package junit;


import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.dcfun.elec.domain.ElecText;


public class TestHibernate {

	@Test
	public void save(){
		Configuration configuration = new Configuration();
		configuration.configure();	//加载类路径 hibernate.cfg.xml和映射文件
		
		SessionFactory sf = configuration.buildSessionFactory();
		Session s = sf.openSession();
		Transaction tr = s.beginTransaction();
		
		//测试操作对象 并保存到数据库
		ElecText elecText = new ElecText();
		elecText.setTextName("测试Hibernate名称");
		elecText.setTextDate(new Date());
		elecText.setTextRemark("测试hibernate备注");
		s.save(elecText);
		
		tr.commit();
		s.close();
		
	}
	
}
