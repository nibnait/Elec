package com.dcfun.elec.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dcfun.elec.base.action.BaseAction;
import com.dcfun.elec.domain.ElecCommonMsg;
import com.dcfun.elec.domain.ElecPopedom;
import com.dcfun.elec.domain.ElecRole;
import com.dcfun.elec.domain.ElecText;
import com.dcfun.elec.domain.ElecUser;
import com.dcfun.elec.service.IElecRoleService;
import com.dcfun.elec.service.IElecTextService;
import com.dcfun.elec.service.IElec_Service;
import com.dcfun.elec.utils.ValueStackUtils;
import com.dcfun.elec.utils.annotation.AnnotationLimit;

@Controller("elecRoleAction")
@Scope(value="prototype")
public class ElecRoleAction extends BaseAction<ElecPopedom>{

	ElecPopedom elecPopedom = this.getModel();
	
	@Resource(name=IElecRoleService.SERVICE_NAME)
	IElecRoleService elecRoleService;
	
	
	/**
	 * @Name: home
	 * @Description: 角色管理的首页显示
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-22 10:25:26
	 * @Parameters: 无
	 * @Return: String: 跳转到/.../roleIndex.jsp
	 */
	@AnnotationLimit(mid="ao",pid="am")
	public String home(){
		
		//1、遍历role表	放入request中
		List<ElecRole> roleList = elecRoleService.findAllRoleList();
		request.setAttribute("roleList", roleList);
		
		//2、组织 两个权限list：	
		//	parentPopedomList	存放所有pid=0的ElecPopedom,放入request中
		//	subPopedomList	存放所有子权限,	根据PList中的elecPopedom.mid 查出其下的所有子权限，然后放入 模型驱动elecPopedom.list中
		List<ElecPopedom> popedomList = elecRoleService.findAllPopedomList();
		request.setAttribute("popedomList", popedomList);
		return "home";
	}


	
	/**
	 * @Name: edit
	 * @Description: 角色管理的编辑页面 && 角色对应的权限 进行回显
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-23 09:34:00
	 * @Parameters: 无
	 * @Return: String: 跳转到/.../roleEdit.jsp
	 */
	@AnnotationLimit(mid="gb",pid="ga")
	public String edit(){
		
		//1、获得roleID，
		String roleID = elecPopedom.getRoleID();
		//根据roleID获取其所具有的所有权限，并标记在 elecPopedom.flag中。
		List<ElecPopedom> popedomList = elecRoleService.findAllPopedomListByRoleID(roleID);
		request.setAttribute("popedomList", popedomList);
		
		return "edit";
	}
	
	/**
	 * @Name: save
	 * @Description: 保存Elec_Role_Popedom表
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-23 19:45:58
	 * @Parameters: elecPopedom模型驱动 VO对象
	 * @Return: 无
	 */
	@AnnotationLimit(mid="gc",pid="ga")
	public String save(){
		
		elecRoleService.save(elecPopedom);
		return "save";
	}
	
	
	
}
