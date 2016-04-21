package com.dcfun.elec.service;

import java.util.List;

import com.dcfun.elec.domain.ElecUser;

public interface IElecUserService {

	public static final String SERVICE_NAME = "com.dcfun.elec.service.impl.ElecUserServiceImpl";

	List<ElecUser> findCollectionByCondition(ElecUser elecUser);
	

}
