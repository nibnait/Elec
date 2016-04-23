package com.dcfun.elec.dao.impl;

import com.dcfun.elec.base.dao.BaseDaoImpl;
import com.dcfun.elec.dao.IElecPopedomDao;
import com.dcfun.elec.dao.IElec_Dao;
import com.dcfun.elec.domain.ElecPopedom;
import com.dcfun.elec.domain.ElecText;

import org.springframework.stereotype.Repository;


@Repository(IElecPopedomDao.SERVICE_NAME)
public class ElecPopedomDaoImpl extends BaseDaoImpl<ElecPopedom> implements IElecPopedomDao{

}
