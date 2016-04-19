package com.dcfun.elec.web.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dcfun.elec.domain.ElecText;
import com.dcfun.elec.service.IElecTextService;
import com.dcfun.elec.web.form.MenuForm;

@Controller("elecMenuAction")
@Scope(value="prototype")
public class ElecMenuAction extends BaseAction<MenuForm>{

	MenuForm menuForm = this.getModel();
	
	

	
}
