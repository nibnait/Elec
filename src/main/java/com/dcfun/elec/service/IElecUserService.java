package com.dcfun.elec.service;

import java.util.List;

import com.dcfun.elec.domain.ElecUser;
import com.dcfun.elec.domain.ElecUserFile;

public interface IElecUserService {

	public static final String SERVICE_NAME = "com.dcfun.elec.service.impl.ElecUserServiceImpl";

	List<ElecUser> findCollectionByCondition(ElecUser elecUser);

	String checkUserByLogonName(String logonName);

	void saveUser(ElecUser elecUser);

	ElecUser findUserByID(String userID);

	ElecUserFile findUserFileByID(String fileID);

	void deleteUserByID(ElecUser elecUser);
	

}
