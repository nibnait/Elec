package com.dcfun.elec.dao.impl;

import com.dcfun.elec.base.dao.BaseDaoImpl;
import com.dcfun.elec.dao.IElecFileUploadDao;
import com.dcfun.elec.dao.IElec_Dao;
import com.dcfun.elec.domain.ElecFileUpload;
import com.dcfun.elec.domain.ElecText;

import org.springframework.stereotype.Repository;


@Repository(IElecFileUploadDao.SERVICE_NAME)
public class ElecFileUploadDaoImpl extends BaseDaoImpl<ElecFileUpload> implements IElecFileUploadDao{

}
