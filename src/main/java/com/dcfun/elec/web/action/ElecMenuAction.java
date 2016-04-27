package com.dcfun.elec.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dcfun.elec.base.action.BaseAction;
import com.dcfun.elec.domain.ElecCommonMsg;
import com.dcfun.elec.domain.ElecPopedom;
import com.dcfun.elec.domain.ElecUser;
import com.dcfun.elec.service.IElecCommonMsgService;
import com.dcfun.elec.service.IElecRoleService;
import com.dcfun.elec.service.IElecUserService;
import com.dcfun.elec.utils.LogonUtils;
import com.dcfun.elec.utils.MD5keyBean;
import com.dcfun.elec.utils.ValueStackUtils;
import com.dcfun.elec.web.form.MenuForm;

@Controller("elecMenuAction")
@Scope(value = "prototype")
public class ElecMenuAction extends BaseAction<MenuForm> {

	MenuForm menuForm = this.getModel();
	
	//注入运行监控service
	@Resource(name=IElecCommonMsgService.SERVICE_NAME)
	IElecCommonMsgService elecCommonMsgService;
	
	@Resource(name=IElecUserService.SERVICE_NAME)
	IElecUserService elecUserService;

	@Resource(name=IElecRoleService.SERVICE_NAME)
	IElecRoleService elecRoleService;


	/**
	 * @Name: menuHome
	 * @Description: 跳转到系统登录的首页
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-19 12:53:49
	 * @Parameters: 无
	 * @Return: String: 跳转到menu/home.jsp
	 */

	public String menuHome() {
		
		/** 2016-04-24 09:18:08 添加 --- 各种登陆校验*/
//		boolean flag = LogonUtils.checkImageNumber(request);
		boolean flag = true;
		if (!flag) {
			this.addActionError("验证码 错误，请重新输入");
			return "error";
		}

		String logonName = menuForm.getName();
		String logonPwd = menuForm.getPassword();
		
		ElecUser elecUser = elecUserService.findUserByLogonName(logonName);
		System.out.println(elecUser.toString());
		
		if (elecUser == null) {
			this.addActionError("用户名 错误，请重新输入");
			return "error";
		}
		
		if (StringUtils.isBlank(logonPwd)) {
			this.addActionError("密码不能为空哦~");
			return "error";
		}else if(!MD5keyBean.getkeyBeanofStr(logonPwd).equals(elecUser.getLogonPwd())) {
			this.addActionError("密码错误，请重新输入");
			return "error";
		}
		
		//记住我
		LogonUtils.remeberMe(logonName,logonPwd,request,response);
		
		//查出此用户角色的所有权限 放到session中
		String popedom = elecRoleService.findPopedomByRoleID(elecUser.getElecRole().getRoleID());
		request.getSession().setAttribute("globle_popedom", popedom);
		
		request.getSession().setAttribute("globle_user", elecUser);
		
		return "menuHome";
	}

	// 标题
	public String title() {

		return "title";
	}

	// 菜单
	public String left() {

		return "left";
	}

	// 框架大小改变
	public String change() {

		return "change";
	}

	// 功能区域显示页面
	/**
	 * @Name: loading
	 * @Description: 功能区域显示页面
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-19 13:37:11
	 * @Parameters: 无
	 * @Return: String: 跳转到menu/loading.jsp
	 */
	public String loading() {
		//查询设备运行情况 放置到浮动框中
		ElecCommonMsg commonMsg = elecCommonMsgService.findCommonMsg();
		ValueStackUtils.pushValueStack(commonMsg);
		return "loading";
	}
	
	/**
	 * @Name: logout
	 * @Description: 跳转到系统登录的首页
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-19 13:37:11
	 * @Parameters: 无
	 * @Return: String: 重新定向到index.jsp
	 */
	public String logout(){
		//清空所有Session
		request.getSession().invalidate();
		
		return "logout";
	}
	
	
	/**
	 * @Name: alermStation
	 * @Description: 显示站点运行情况
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-19 20:33:43
	 * @Parameters: 无
	 * @Return: String: 跳转到menu/alermStation.jsp
	 */
	//站点运行情况
	public String alermStation(){
		ElecCommonMsg commonMsg = elecCommonMsgService.findCommonMsg();
		ValueStackUtils.pushValueStack(commonMsg);
		return "alermStation";
	}
	
	/**
	 * @Name: alermDevice
	 * @Description: 显示设备运行情况
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-19 20:33:43
	 * @Parameters: 无
	 * @Return: String: 跳转到menu/alermDevice.jsp
	 */
	//设备运行情况
	public String alermDevice(){
		ElecCommonMsg commonMsg = elecCommonMsgService.findCommonMsg();
		ValueStackUtils.pushValueStack(commonMsg);
		return "alermDevice";
	}
	
	/**
	 * @Name:  showMenu
	 * @Description: 根据权限 显示左侧菜单栏
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-24 13:56:49
	 * @Parameters: String 【request.getSession().getAttribute("globle_popedom");】
	 * @Return: String 返回 popedomjson格式
	 */
	public String showMenu(){
		
		String popedomStr = (String) request.getSession().getAttribute("globle_popedom");
		List<ElecPopedom> popedomList = elecRoleService.findPopedomByString(popedomStr);
		for(ElecPopedom popedom:popedomList){
			if (popedom.getPid().equals("0")) {
				popedom.setOpen("true");	
			}
		}
		//将List转化成json，只需要将list集合放置到栈顶
		ValueStackUtils.pushValueStack(popedomList);

		
		/**2016-04-24 22:49:11 添加  --- 根据角色 跳转*/
		ElecUser user =  (ElecUser) request.getSession().getAttribute("globle_user");
		String roleID = user.getElecRole().getRoleID();
		if (!roleID.equals("1")) {// 如果是非系统管理员
			for(ElecPopedom popedom:popedomList){
				if ("an".equals(popedom.getMid()) && "am".equals(popedom.getPid())) {
					popedom.setUrl("../system/elecUserAction_edit.do?userID="+user.getUserID()+"&roleflag=1");
				}
			}
		}
		

		return "showMenu";
	}
}
