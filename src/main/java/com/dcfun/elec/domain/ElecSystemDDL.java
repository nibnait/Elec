package com.dcfun.elec.domain;

@SuppressWarnings("serial")
public class ElecSystemDDL implements java.io.Serializable {
	
	private Integer seqID;		//主键ID(自增长)
	private String keyword;		//数据类型
	private Integer ddlCode;	//数据项的code
	private String ddlName;		//数据项的value
	
	public ElecSystemDDL(){
		
	}
	
	/**投影对象中的属性*/
	public ElecSystemDDL(String keyword){
		this.keyword = keyword;
	}
	
	public Integer getSeqID() {
		return seqID;
	}
	public void setSeqID(Integer seqID) {
		this.seqID = seqID;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getDdlCode() {
		return ddlCode;
	}
	public void setDdlCode(Integer ddlCode) {
		this.ddlCode = ddlCode;
	}
	public String getDdlName() {
		return ddlName;
	}
	public void setDdlName(String ddlName) {
		this.ddlName = ddlName;
	}
	
	/**非持久化javabean的属性*/
	//数据类型
	private String keywordname;
	/**
	 * 用来判断执行到业务标识
	 * add：此时【保存】表示在已有的类型上进行编辑和修改
	 * new：此时【保存】表示新增一种数据类型
	 */
	private String typeflag;
	
	//数据项的值，数组
	private String [] itemname;

	public String getKeywordname() {
		return keywordname;
	}

	public void setKeywordname(String keywordname) {
		this.keywordname = keywordname;
	}

	public String getTypeflag() {
		return typeflag;
	}

	public void setTypeflag(String typeflag) {
		this.typeflag = typeflag;
	}

	public String[] getItemname() {
		return itemname;
	}

	public void setItemname(String[] itemname) {
		this.itemname = itemname;
	}
	
}
