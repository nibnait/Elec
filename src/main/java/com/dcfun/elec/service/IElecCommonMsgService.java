package com.dcfun.elec.service;

import com.dcfun.elec.domain.ElecCommonMsg;

public interface IElecCommonMsgService {

	public static final String SERVICE_NAME = "com.dcfun.elec.service.impl.ElecCommonMsgServiceImpl";
	
	void saveElecCommonMsg(ElecCommonMsg elecCommonMsg);

	ElecCommonMsg findCommonMsg();
	
}
