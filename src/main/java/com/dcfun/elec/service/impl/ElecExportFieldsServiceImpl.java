package com.dcfun.elec.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dcfun.elec.dao.IElecExportFieldsDao;
import com.dcfun.elec.dao.IElec_Dao;
import com.dcfun.elec.domain.ElecExportFields;
import com.dcfun.elec.domain.ElecText;
import com.dcfun.elec.service.IElecExportFieldsService;
import com.dcfun.elec.service.IElec_Service;


@Service(IElecExportFieldsService.SERVICE_NAME)
@Transactional(readOnly=true)
public class ElecExportFieldsServiceImpl implements IElecExportFieldsService {

	@Resource(name=IElecExportFieldsDao.SERVICE_NAME)
	IElecExportFieldsDao elecExportFieldsDao;

	@Override
	public ElecExportFields findElecExportFieldsById(String belongTo) {
		return elecExportFieldsDao.findObjectById(belongTo);
	}

	/**
	 * @Name: saveSetExportExcel
	 * @Description: 更新保存导出设置的操作
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-26 01:49:44
	 * @Parameters: ElecExportFields  VO对象
	 * @Return: void
	 */
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveSetExportExcel(ElecExportFields elecExportFields) {
		elecExportFieldsDao.update(elecExportFields);
	}
	
}
