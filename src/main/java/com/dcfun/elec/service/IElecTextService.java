package com.dcfun.elec.service;

import com.dcfun.elec.domain.ElecText;

public interface IElecTextService {

	public static final String SERVICE_NAME = "com.dcfun.elec.service.impl.ElecTextServiceImpl";
	
	void saveElecText(ElecText elecText);

}
