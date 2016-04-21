package com.dcfun.elec.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dcfun.elec.dao.IElec_Dao;
import com.dcfun.elec.domain.ElecText;
import com.dcfun.elec.service.IElec_Service;


@Service(IElec_Service.SERVICE_NAME)
@Transactional(readOnly=true)
public class Elec_ServiceImpl implements IElec_Service {

	@Resource(name=IElec_Dao.SERVICE_NAME)
	IElec_Dao elec_Dao;
	
	/**
	 * @Name: saveElecText
	 * @Description: 保存
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-21 15:02:38
	 * @Parameters: elecText
	 * @Return: void
	 */
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecText(ElecText elecText) {
		elec_Dao.save(elecText);
	}
}
