 package com.dcfun.elec.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dcfun.elec.base.action.BaseAction;
import com.dcfun.elec.domain.ElecSystemDDL;
import com.dcfun.elec.service.IElecSystemDDLService;

@Controller("elecSystemDDLAction")
@Scope(value="prototype")
public class ElecSystemDDLAction extends BaseAction<ElecSystemDDL>{

	ElecSystemDDL elecSystemDDL = this.getModel();
	
	@Resource(name=IElecSystemDDLService.SERVICE_NAME)
	IElecSystemDDLService elecSystemDDLService;
	
	
	/**
	 * @Name: home
	 * @Description: 数据字典的首页显示
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-20 12:53:28
	 * @Parameters: 无
	 * @Return: String: 跳转到ystem/dictionaryIndex.jsp
	 */
	public String home(){
		List<ElecSystemDDL> list = elecSystemDDLService.findCollectionByDistinct();
//		ValueUtils.putValueStack(list);
		request.setAttribute("list", list);
		return "home";
	}
	
	/**
	 * @Name: edit
	 * @Description: 数据字典的编辑页面回显
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-20 17:09:25
	 * @Parameters: 无
	 * @Return: String: 跳转到system/dictionaryEdit.jsp
	 */
	public String edit(){
		List<ElecSystemDDL> list = elecSystemDDLService.findCollectionByKeyword(elecSystemDDL.getKeyword());
//		ValueUtils.putValueStack(list);
		request.setAttribute("list", list);
		return "edit";
	}
	
	/**
	 * @Name: save
	 * @Description: 编辑或新增数据字典类型
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-20 21:34:58
	 * @Parameters: 无
	 * @Return: String: 跳转到dictionaryIndex.jsp
	 */
	public String save(){

		elecSystemDDLService.saveElecSystemDDL(elecSystemDDL);

		
		return "save";
	}
	
}
