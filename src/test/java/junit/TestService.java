package junit;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dcfun.elec.domain.ElecText;
import com.dcfun.elec.service.IElecTextService;

public class TestService {
	
	ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
	IElecTextService elecTextService = (IElecTextService) ac.getBean(IElecTextService.SERVICE_NAME);
	

	@Test
	public void save() {
		
		ElecText elecText = new ElecText();
		elecText.setTextName("测试spring名称3");
		elecText.setTextDate(new Date());
		elecText.setTextRemark("测试spring备注3");
		elecTextService.saveElecText(elecText);
		
	}
	
	@Test
	public void findCollectionByConditionNoPage(){
		
		//Condition为Model模型驱动
		ElecText elecText = new ElecText();
		elecText.setTextName("测试");
		elecText.setTextRemark("测试");
		
		
//		elecTextService.findCollectionByConditionNoPage(elecText);
	}

}
