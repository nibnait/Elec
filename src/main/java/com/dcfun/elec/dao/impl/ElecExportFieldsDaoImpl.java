package com.dcfun.elec.dao.impl;

import com.dcfun.elec.base.dao.BaseDaoImpl;
import com.dcfun.elec.dao.IElecExportFieldsDao;
import com.dcfun.elec.dao.IElec_Dao;
import com.dcfun.elec.domain.ElecExportFields;
import com.dcfun.elec.domain.ElecText;

import org.springframework.stereotype.Repository;


@Repository(IElecExportFieldsDao.SERVICE_NAME)
public class ElecExportFieldsDaoImpl extends BaseDaoImpl<ElecExportFields> implements IElecExportFieldsDao{

}
