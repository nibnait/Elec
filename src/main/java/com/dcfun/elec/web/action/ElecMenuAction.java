package com.dcfun.elec.web.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dcfun.elec.base.action.BaseAction;
import com.dcfun.elec.domain.ElecCommonMsg;
import com.dcfun.elec.domain.ElecText;
import com.dcfun.elec.service.IElecCommonMsgService;
import com.dcfun.elec.service.IElecTextService;
import com.dcfun.elec.utils.ValueUtils;
import com.dcfun.elec.web.form.MenuForm;

@Controller("elecMenuAction")
@Scope(value = "prototype")
public class ElecMenuAction extends BaseAction<MenuForm> {

	MenuForm menuForm = this.getModel();
	
	//注入运行监控service
	@Resource(name=IElecCommonMsgService.SERVICE_NAME)
	IElecCommonMsgService elecCommonMsgService;

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
//		System.out.println(menuForm.getName() + " ... " + menuForm.getPassword());
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
	public String loading() {

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
		ValueUtils.putValueStack(commonMsg);
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
		ValueUtils.putValueStack(commonMsg);
		return "alermDevice";
	}
	
}
