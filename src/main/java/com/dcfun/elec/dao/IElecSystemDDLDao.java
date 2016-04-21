package com.dcfun.elec.dao;

import java.util.List;

import com.dcfun.elec.base.dao.IBaseDao;
import com.dcfun.elec.domain.ElecCommonMsgContent;
import com.dcfun.elec.domain.ElecSystemDDL;

public interface IElecSystemDDLDao extends IBaseDao<ElecSystemDDL>{

	public static final String SERVICE_NAME = "com.dcfun.elec.dao.impl.ElecSystemDDLDaoImpl";

	List<ElecSystemDDL> findSystemDDLListByDistinct();

	String findDdlNameByKeywordAndDdlCode(String string, String sexID);

}
