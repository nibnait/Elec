package com.dcfun.elec.dao;

import com.dcfun.elec.base.dao.IBaseDao;
import com.dcfun.elec.domain.ElecText;
import com.dcfun.elec.domain.ElecUser;
import com.dcfun.elec.domain.ElecUserFile;

public interface IElecUserFileDao extends IBaseDao<ElecUserFile>{

	public static final String SERVICE_NAME = "com.dcfun.elec.dao.impl.ElecUserFileDaoImpl";

}
