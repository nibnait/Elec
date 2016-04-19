package com.dcfun.elec.domain;

import java.io.Serializable;
import java.util.Date;

public class ElecText implements Serializable{
	private String textID;
	private String textName;
	private Date textDate;
	private String textRemark;
	
	public String getTextID() {
		return textID;
	}
	public void setTextID(String textID) {
		this.textID = textID;
	}
	public String getTextName() {
		return textName;
	}
	public void setTextName(String textname) {
		this.textName = textname;
	}
	public Date getTextDate() {
		return textDate;
	}
	public void setTextDate(Date textDate) {
		this.textDate = textDate;
	}
	public String getTextRemark() {
		return textRemark;
	}
	public void setTextRemark(String textRemark) {
		this.textRemark = textRemark;
	}
	
	

}
