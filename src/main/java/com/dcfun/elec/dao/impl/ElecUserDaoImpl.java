package com.dcfun.elec.dao.impl;

import com.dcfun.elec.base.dao.BaseDaoImpl;
import com.dcfun.elec.dao.IElecUserDao;
import com.dcfun.elec.dao.IElec_Dao;
import com.dcfun.elec.domain.ElecText;
import com.dcfun.elec.domain.ElecUser;

import org.springframework.stereotype.Repository;


@Repository(IElecUserDao.SERVICE_NAME)
public class ElecUserDaoImpl extends BaseDaoImpl<ElecUser> implements IElecUserDao{

}
