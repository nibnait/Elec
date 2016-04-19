package com.dcfun.elec.dao.impl;

import com.dcfun.elec.base.dao.BaseDaoImpl;
import com.dcfun.elec.dao.IElecTextDao;
import com.dcfun.elec.domain.ElecText;

import org.springframework.stereotype.Repository;


@Repository(IElecTextDao.SERVICE_NAME)
public class ElecTextDaoImpl extends BaseDaoImpl<ElecText> implements IElecTextDao{

}
