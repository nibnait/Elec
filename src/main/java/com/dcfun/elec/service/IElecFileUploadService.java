package com.dcfun.elec.service;

import java.util.List;

import com.dcfun.elec.domain.ElecFileUpload;

public interface IElecFileUploadService {

	public static final String SERVICE_NAME = "com.dcfun.elec.service.impl.ElecFileUploadServiceImpl";

	void saveFileUpload(ElecFileUpload elecFileUpload);

	List<ElecFileUpload> findFileUploadListByCondition(
			ElecFileUpload elecFileUpload);

	ElecFileUpload findFileByID(Integer fileID);

	List<ElecFileUpload> findFileUploadListByLuceneCondition(
			ElecFileUpload elecFileUpload);

	void deleteFileUploadByID(Integer seqId);
	

}
