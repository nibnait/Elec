package com.dcfun.elec.service;

import java.util.List;

import com.dcfun.elec.domain.ElecSystemDDL;


public interface IElecSystemDDLService {

	public static final String SERVICE_NAME = "com.dcfun.elec.service.impl.ElecSystemDDLServiceImpl";

	List<ElecSystemDDL> findCollectionByDistinct();

	List<ElecSystemDDL> findCollectionByKeyword(String keyword);

	void saveElecSystemDDL(ElecSystemDDL elecSystemDDL);

	

}
