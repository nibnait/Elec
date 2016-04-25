package com.dcfun.elec.dao;

import java.util.List;
import java.util.Map;

import com.dcfun.elec.base.dao.IBaseDao;
import com.dcfun.elec.domain.ElecText;
import com.dcfun.elec.domain.ElecUser;

public interface IElecUserDao extends IBaseDao<ElecUser>{

	public static final String SERVICE_NAME = "com.dcfun.elec.dao.impl.ElecUserDaoImpl";

	List<ElecUser> findCollectionByConditionNoPageWithSql(
			Map<String, Object> condition, Map<String, String> orderby);

}
