package com.dcfun.elec.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dcfun.elec.base.action.BaseAction;
import com.dcfun.elec.domain.ElecSystemDDL;
import com.dcfun.elec.domain.ElecUser;
import com.dcfun.elec.service.IElecSystemDDLService;
import com.dcfun.elec.service.IElecUserService;

@Controller("elecUserAction")
@Scope(value="prototype")
public class ElecUserAction extends BaseAction<ElecUser>{

	ElecUser elecUser = this.getModel();
	
	
	@Resource(name=IElecUserService.SERVICE_NAME)
	IElecUserService elecUserService;
	
	@Resource(name=IElecSystemDDLService.SERVICE_NAME)
	IElecSystemDDLService elecSystemDDLService;
	
	/**
	 * @Name: home
	 * @Description: 用户管理首页显示
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-21 00:14:05
	 * @Parameters: 无
	 * @Return: String: 跳转到system/userIndex.jsp
	 */
	public String home(){
//		1：加载数据类型是所属单位的数据字典的集合，遍历在页面的下拉菜单中
		List<ElecSystemDDL> jctList = elecSystemDDLService.findCollectionByKeyword("所属单位");
		request.setAttribute("jctList", jctList);

//		2：根据组织页面中传递的查询条件，查询用户表，返回List<ElecUser>
		List<ElecUser> userList = elecUserService.findCollectionByCondition(elecUser);
		request.setAttribute("userList", userList);
		
//		3：数据字典的转换

		
		return "home";
	}
	
	
	/**
	 * @Name: add
	 * @Description: 跳转到用户管理的添加页面
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-21 09:15:53
	 * @Parameters: 无
	 * @Return: String: 跳转到system/userAdd.jsp
	 */
	public String add(){
//		1：加载数据字典，用来遍历性别，职位，所属单位，是否在职
		this.initSystemDDL();
		return "add";
	}

	private void initSystemDDL() {
		List<ElecSystemDDL> sexList = elecSystemDDLService.findCollectionByKeyword("性别");
		request.setAttribute("sexList", sexList);
		List<ElecSystemDDL> postList = elecSystemDDLService.findCollectionByKeyword("职位");
		request.setAttribute("postList", postList);
		List<ElecSystemDDL> jctList = elecSystemDDLService.findCollectionByKeyword("所属单位");
		request.setAttribute("jctList", jctList);
		List<ElecSystemDDL> isDutyList = elecSystemDDLService.findCollectionByKeyword("是否在职");
		request.setAttribute("isDutyList", isDutyList);
	}
}
