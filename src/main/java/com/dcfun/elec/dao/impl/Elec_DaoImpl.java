package com.dcfun.elec.dao.impl;

import com.dcfun.elec.base.dao.BaseDaoImpl;
import com.dcfun.elec.dao.IElec_Dao;
import com.dcfun.elec.domain.ElecText;

import org.springframework.stereotype.Repository;


@Repository(IElec_Dao.SERVICE_NAME)
public class Elec_DaoImpl extends BaseDaoImpl<ElecText> implements IElec_Dao{

}
