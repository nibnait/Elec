package junit;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dcfun.elec.dao.IElecTextDao;
import com.dcfun.elec.domain.ElecText;

public class TestDao {

	ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
	@Test
	public void save() {

		

		IElecTextDao elecTextDao = (IElecTextDao) ac.getBean(IElecTextDao.SERVICE_NAME);
		
		ElecText elecText = new ElecText();
		elecText.setTextName("测试Dao名称");
		elecText.setTextDate(new Date());
		elecText.setTextRemark("测试Dao备注");
		elecTextDao.save(elecText);

	}

	@Test
	public void update() {

		IElecTextDao elecTextDao = (IElecTextDao) ac.getBean(IElecTextDao.SERVICE_NAME);
		
		ElecText elecText = new ElecText();
		elecText.setTextID("4028368154287a4f0154287a52c80001");
		elecText.setTextName("测试Dao名称0---更新");
		elecText.setTextDate(new Date());
		elecText.setTextRemark("测试Dao备注--更新");
		elecTextDao.update(elecText);

	}	
	
	
	@Test
	public void findObjectById(){
		IElecTextDao elecTextDao = (IElecTextDao) ac.getBean(IElecTextDao.SERVICE_NAME);
		
		ElecText elecText = elecTextDao.findObjectById("40283681542934ad0154293523390002");
		System.out.println("name:"+elecText.getTextName());
	}
	
	@Test
	public void deleteObjectByIds(){
		IElecTextDao elecTextDao = (IElecTextDao) ac.getBean(IElecTextDao.SERVICE_NAME);
		
		Serializable[] ids = {"40283681542903300154290334480001","ddd"};
		elecTextDao.deleteObjectByIDs(ids);
	}
	
	@Test
	public void deleteObjectByCollection(){
		
		IElecTextDao elecTextDao = (IElecTextDao) ac.getBean(IElecTextDao.SERVICE_NAME);
		List<ElecText> list = new ArrayList<ElecText>();
		ElecText elecText1 = new ElecText();
		elecText1.setTextID("402895ef49f46c5f0149f46c64cd0001");
		
		ElecText elecText2 = new ElecText();
		elecText2.setTextID("402895ef49f46cd80149f46d55950001");
		
		ElecText elecText3 = new ElecText();
		elecText3.setTextID("402895ef49f46f060149f46f0cbb0001");
		
		list.add(elecText1);
		list.add(elecText2);
		list.add(elecText3);
		
		elecTextDao.deleteObjectByCollection(list);
	}
	
}
