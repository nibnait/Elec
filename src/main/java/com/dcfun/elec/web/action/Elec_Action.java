package com.dcfun.elec.web.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dcfun.elec.base.action.BaseAction;
import com.dcfun.elec.domain.ElecCommonMsg;
import com.dcfun.elec.domain.ElecText;
import com.dcfun.elec.service.IElecTextService;
import com.dcfun.elec.service.IElec_Service;
import com.dcfun.elec.utils.ValueStackUtils;

@Controller("elec_Action")
@Scope(value="prototype")
public class Elec_Action extends BaseAction<ElecText>{

	ElecText elecText = this.getModel();
	
	@Resource(name=IElec_Service.SERVICE_NAME)
	IElec_Service elec_Service;
	
	
	/**
	 * @Name: home
	 * @Description: 的首页显示
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-20 12:46:07
	 * @Parameters: 无
	 * @Return: String: 跳转到
	 */
	public String home(){
		
		
		return "home";
	}
}
