package com.dcfun.elec.domain;


@SuppressWarnings("serial")
public class ElecExportFields implements java.io.Serializable {
	
	private String belongTo;		//所属模块（自然主键），如用户管理为：5-1
	private String expNameList;		//导出字段的中文名
	private String expFieldName;	//导出字段的英文名
	private String noExpNameList;	//未导出字段的中文名
	private String noExpFieldName;	//未导出字段的英文名
	
	public String getBelongTo() {
		return belongTo;
	}
	public void setBelongTo(String belongTo) {
		this.belongTo = belongTo;
	}
	public String getExpNameList() {
		return expNameList;
	}
	public void setExpNameList(String expNameList) {
		this.expNameList = expNameList;
	}
	public String getExpFieldName() {
		return expFieldName;
	}
	public void setExpFieldName(String expFieldName) {
		this.expFieldName = expFieldName;
	}
	public String getNoExpNameList() {
		return noExpNameList;
	}
	public void setNoExpNameList(String noExpNameList) {
		this.noExpNameList = noExpNameList;
	}
	public String getNoExpFieldName() {
		return noExpFieldName;
	}
	public void setNoExpFieldName(String noExpFieldName) {
		this.noExpFieldName = noExpFieldName;
	}
	
	
	
	
}
