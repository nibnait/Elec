package com.dcfun.elec.domain;

import java.util.ArrayList;
import java.util.List;



@SuppressWarnings("serial")
public class ElecPopedom implements java.io.Serializable {
	
	
	private String mid;			//权限Code（主键ID）
	private String pid;			//父级权限code，如果已经是根节点则为0
	private String name;		//权限名称
	private String url;			//权限在系统中执行访问地址的URL
	private String icon;		//如果是菜单，则为显示图片的URL
	private String target;		//如果是菜单，链接执行的Frame区域名称
	private boolean isParent;	//是否是父节点，父节点为true，子节点为false
	private boolean isMenu;		//是否是系统菜单结构
	
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}
	public boolean getIsMenu() {
		return isMenu;
	}
	public void setIsMenu(boolean isMenu) {
		this.isMenu = isMenu;
	}
	
	/**非持久化javabean属性*/
	//表示如果一个父包含的所有子的集合
	private List<ElecPopedom> list = new ArrayList<>();
	//角色ID
	private String roleID;
	/**
	 * 添加一个标识，判断页面的复选框是否被选中，该标识要放置到ElecPopedom对象中
   flag=1：选中
   flag=2：没有被选中
	 */
	private String flag;
	
	//页面中传递的选中的功能权限
	private String [] selectoper;
	
	//设置所有父节点初始化时展开
	private String open;

	
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String[] getSelectoper() {
		return selectoper;
	}
	public void setSelectoper(String[] selectoper) {
		this.selectoper = selectoper;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	public List<ElecPopedom> getList() {
		return list;
	}
	public void setList(List<ElecPopedom> list) {
		this.list = list;
	}
	
}
