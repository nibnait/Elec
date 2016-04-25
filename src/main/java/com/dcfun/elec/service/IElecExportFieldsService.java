package com.dcfun.elec.service;

import com.dcfun.elec.domain.ElecExportFields;

public interface IElecExportFieldsService {

	public static final String SERVICE_NAME = "com.dcfun.elec.service.impl.ElecExportFieldsServiceImpl";

	ElecExportFields findElecExportFieldsById(String belongTo);

	void saveSetExportExcel(ElecExportFields elecExportFields);
	

}
