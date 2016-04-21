package com.dcfun.elec.dao.impl;

import com.dcfun.elec.base.dao.BaseDaoImpl;
import com.dcfun.elec.dao.IElecUserDao;
import com.dcfun.elec.dao.IElecUserFileDao;
import com.dcfun.elec.dao.IElec_Dao;
import com.dcfun.elec.domain.ElecText;
import com.dcfun.elec.domain.ElecUser;
import com.dcfun.elec.domain.ElecUserFile;

import org.springframework.stereotype.Repository;


@Repository(IElecUserFileDao.SERVICE_NAME)
public class ElecUserFileDaoImpl extends BaseDaoImpl<ElecUserFile> implements IElecUserFileDao{

}
