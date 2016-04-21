package com.dcfun.elec.domain;

import java.util.Date;

@SuppressWarnings("serial")
public class ElecUserFile implements java.io.Serializable {
	

	
	private String fileID;		//主键ID
	private String fileName;	//文件名
	private String fileURL;		//文件路径
	private Date progressTime;	//上传时间
	
	private ElecUser elecUser;
	
	public ElecUser getElecUser() {
		return elecUser;
	}

	public void setElecUser(ElecUser elecUser) {
		this.elecUser = elecUser;
	}

	public String getFileID() {
		return fileID;
	}

	public void setFileID(String fileID) {
		this.fileID = fileID;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileURL() {
		return fileURL;
	}

	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}

	public Date getProgressTime() {
		return progressTime;
	}

	public void setProgressTime(Date progressTime) {
		this.progressTime = progressTime;
	}

	
	
}
