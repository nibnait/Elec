package com.dcfun.elec.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dcfun.elec.dao.IElecTextDao;
import com.dcfun.elec.domain.ElecText;
import com.dcfun.elec.service.IElecTextService;


@Service(IElecTextService.SERVICE_NAME)
@Transactional(readOnly=true)
public class ElecTextServiceImpl implements IElecTextService {

	@Resource(name=IElecTextDao.SERVICE_NAME)
	IElecTextDao elecTextDao;
	
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecText(ElecText elecText) {
		elecTextDao.save(elecText);
	}

	@Override
	public void findCollectionByConditionNoPage(ElecText elecText) {

//		String whereStr = buildWhere(elecText);
		
	}

	private void buildWhere(ElecText elecText) {

		
	}
	

}
