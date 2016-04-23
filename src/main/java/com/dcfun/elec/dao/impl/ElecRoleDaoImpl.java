package com.dcfun.elec.dao.impl;

import com.dcfun.elec.base.dao.BaseDaoImpl;
import com.dcfun.elec.dao.IElecRoleDao;
import com.dcfun.elec.dao.IElec_Dao;
import com.dcfun.elec.domain.ElecRole;
import com.dcfun.elec.domain.ElecText;

import org.springframework.stereotype.Repository;


@Repository(IElecRoleDao.SERVICE_NAME)
public class ElecRoleDaoImpl extends BaseDaoImpl<ElecRole> implements IElecRoleDao{

}
