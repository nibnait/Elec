package com.dcfun.elec.service;

import java.util.List;

import com.dcfun.elec.domain.ElecPopedom;
import com.dcfun.elec.domain.ElecRole;
import com.dcfun.elec.domain.ElecUser;

public interface IElecRoleService {

	public static final String SERVICE_NAME = "com.dcfun.elec.service.impl.ElecRoleServiceImpl";

	List<ElecRole> findAllRoleList();

	List<ElecPopedom> findAllPopedomList();

	List<ElecPopedom> findAllPopedomListByRoleID(String roleID);

	void save(ElecPopedom elecPopedom);
	

}
