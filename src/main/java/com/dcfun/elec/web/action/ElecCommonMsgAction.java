package com.dcfun.elec.web.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dcfun.elec.base.action.BaseAction;
import com.dcfun.elec.domain.ElecCommonMsg;
import com.dcfun.elec.domain.ElecText;
import com.dcfun.elec.service.IElecCommonMsgService;
import com.dcfun.elec.service.IElecTextService;
import com.dcfun.elec.utils.Util_ValueStack;

@Controller("elecCommonMsgAction")
@Scope(value="prototype")
public class ElecCommonMsgAction extends BaseAction<ElecCommonMsg>{

	ElecCommonMsg elecCommonMsg = this.getModel();
	
	//注入运行监控service
	@Resource(name=IElecCommonMsgService.SERVICE_NAME)
	IElecCommonMsgService elecCommonMsgService;
	
	/**
	 * @Name: home
	 * @Description: 运行监控的首页显示
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-19 14:52:12
	 * @Parameters: 无
	 * @Return: String: 跳转到actingIndex.jsp
	 */
	public String home(){
		ElecCommonMsg commonMsg = elecCommonMsgService.findCommonMsg();
		Util_ValueStack.pushValueStack(commonMsg);
		return "home";
	}
	
	/**  
	* @Name: save
	* @Description: 保存运行监控数据
	* @Author: dcfun
	* @Version: V1.00 （版本号）
	* @Create Date: 2016-04-19 18:57:18
	* @Parameters: 无
	* @Return: String：重定向到system/actingIndex.jsp（再查询）
	*/
	public String save(){
		elecCommonMsgService.saveElecCommonMsg(elecCommonMsg);
		return "save";
	}
	
	/**  
	* @Name: actingView
	* @Description: 使用highsliderJS完成查询设备运行情况的详细信息
	* @Author: dcfun
	* @Version: V1.00 （版本号）
	* @Create Date: 2016-04-19 18:58:02
	* @Parameters: 无
	* @Return: String：跳转到system/actingView.jsp
	*/
	public String actingView(){
		//查询运行监控的数据
		//1：查询数据库运行监控表的数据，返回惟一ElecCommonMsg
		ElecCommonMsg commonMsg = elecCommonMsgService.findCommonMsg();
		//2：将ElecCommonMsg对象压入栈顶，支持表单回显
		Util_ValueStack.pushValueStack(commonMsg);
		return "actingView";
	}
	
}
