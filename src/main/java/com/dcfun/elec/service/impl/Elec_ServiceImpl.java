package com.dcfun.elec.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcfun.elec.dao.IElec_Dao;
import com.dcfun.elec.service.IElec_Service;


@Service(IElec_Service.SERVICE_NAME)
@Transactional(readOnly=true)
public class Elec_ServiceImpl implements IElec_Service {

	@Resource(name=IElec_Dao.SERVICE_NAME)
	IElec_Dao elec_Dao;
	
	
	
	
}
