package com.dcfun.elec.domain;

@SuppressWarnings("serial")
public class ElecCommonMsgContent implements java.io.Serializable {
	
	
	private String comID;			//主键ID
	private String type;			//判断站点和设备运行的标识
	private String content;			//数据内容
	private Integer orderby;		//数据显示排序
	
	public String getComID() {
		return comID;
	}
	public void setComID(String comID) {
		this.comID = comID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getOrderby() {
		return orderby;
	}
	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}
	
	
	
}
