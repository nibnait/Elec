package com.dcfun.elec.web.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dcfun.elec.base.action.BaseAction;
import com.dcfun.elec.domain.ElecText;
import com.dcfun.elec.service.IElecTextService;

@Controller("elecTextAction")
@Scope(value="prototype")
public class ElecTextAction extends BaseAction<ElecText>{

	ElecText elecText = this.getModel();
	
	@Resource(name=IElecTextService.SERVICE_NAME)
	IElecTextService elecTextService;
	
	public String save(){
		elecTextService.saveElecText(elecText);
		return "save";
	}
}
