package com.dcfun.elec.dao.impl;

import com.dcfun.elec.base.dao.BaseDaoImpl;
import com.dcfun.elec.dao.IElecCommonMsgDao;
import com.dcfun.elec.dao.IElecTextDao;
import com.dcfun.elec.domain.ElecCommonMsg;
import com.dcfun.elec.domain.ElecText;

import org.springframework.stereotype.Repository;


@Repository(IElecCommonMsgDao.SERVICE_NAME)
public class ElecCommonMsgDaoImpl extends BaseDaoImpl<ElecCommonMsg> implements IElecCommonMsgDao{

}
