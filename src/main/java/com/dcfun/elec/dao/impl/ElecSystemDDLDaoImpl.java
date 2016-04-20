package com.dcfun.elec.dao.impl;

import java.util.List;

import com.dcfun.elec.base.dao.BaseDaoImpl;
import com.dcfun.elec.dao.IElecSystemDDLDao;
import com.dcfun.elec.dao.IElecTextDao;
import com.dcfun.elec.domain.ElecSystemDDL;
import com.dcfun.elec.domain.ElecText;

import org.springframework.stereotype.Repository;


@Repository(IElecSystemDDLDao.SERVICE_NAME)
public class ElecSystemDDLDaoImpl extends BaseDaoImpl<ElecSystemDDL> implements IElecSystemDDLDao{

	@Override
	public List<ElecSystemDDL> findCollectionByDistinct() {

		String hql = "SELECT DISTINCT new ElecSystemDDL(keyword) FROM ElecSystemDDL";
		List<ElecSystemDDL> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
	

}
