package com.dcfun.elec.domain;

import java.util.HashSet;
import java.util.Set;


@SuppressWarnings("serial")
public class ElecRole implements java.io.Serializable {
	
	private String roleID;		//主键ID
	private String roleName;	//角色名称
	
	/** 2016-04-23 23:45:25添加 Set<ElecUser>*/
	private Set<ElecUser> elecUsers = new HashSet<ElecUser>();
	
	public Set<ElecUser> getElecUsers() {
		return elecUsers;
	}
	public void setElecUsers(Set<ElecUser> elecUsers) {
		this.elecUsers = elecUsers;
	}
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
