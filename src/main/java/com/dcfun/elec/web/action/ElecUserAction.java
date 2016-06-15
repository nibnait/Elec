package com.dcfun.elec.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dcfun.elec.base.action.BaseAction;
import com.dcfun.elec.domain.ElecRole;
import com.dcfun.elec.domain.ElecSystemDDL;
import com.dcfun.elec.domain.ElecUser;
import com.dcfun.elec.domain.ElecUserFile;
import com.dcfun.elec.service.IElecRoleService;
import com.dcfun.elec.service.IElecSystemDDLService;
import com.dcfun.elec.service.IElecUserService;
import com.dcfun.elec.utils.ValueStackUtils;
import com.dcfun.elec.utils.annotation.AnnotationLimit;

@Controller("elecUserAction")
@Scope(value="prototype")
public class ElecUserAction extends BaseAction<ElecUser>{

	ElecUser elecUser = this.getModel();
	
	@Resource(name=IElecUserService.SERVICE_NAME)
	IElecUserService elecUserService;
	
	@Resource(name=IElecSystemDDLService.SERVICE_NAME)
	IElecSystemDDLService elecSystemDDLService;
	
	@Resource(name=IElecRoleService.SERVICE_NAME)
	IElecRoleService elecRoleService;
	
	
	/**
	 * @Name: home
	 * @Description: 用户管理首页显示
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-21 00:14:05
	 * @Parameters: 无
	 * @Return: String: 跳转到system/userIndex.jsp
	 */
	@AnnotationLimit(mid="an",pid="am")
	public String home(){
//		1：加载数据类型是所属单位的数据字典的集合，遍历在页面的下拉菜单中
		
		List<ElecSystemDDL> jctList = elecSystemDDLService.findSystemDDLListByKeyword("所属单位");
		request.setAttribute("jctList", jctList);
//		Util_ValueStack.putValueStack("jctList",jctList);

//		2：根据组织页面中传递的查询条件，查询用户表，返回List<ElecUser>
		List<ElecUser> userList = elecUserService.findUserListByCondition(elecUser);
		request.setAttribute("userList", userList);
		
		/**2016-04-28 00:23:23  添加分页  begin*/
		String initPage = request.getParameter("initPage");//判断执行的业务标识，initpage=1,则采用分页
		//执行ajax操作跳转到userList.jsp
		if (initPage!=null && initPage.equals("1")) {
			return "list";
		}
		
		/**2016-04-28 00:23:23  添加分页  end*/
		
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
	@AnnotationLimit(mid="fb",pid="fa")
	public String add(){
//		1：加载数据字典，用来遍历性别，职位，所属单位，是否在职
		this.initSystemDDL();
		return "add";
	}

	private void initSystemDDL() {
		List<ElecSystemDDL> sexList = elecSystemDDLService.findSystemDDLListByKeyword("性别");
		request.setAttribute("sexList", sexList);
		List<ElecSystemDDL> postList = elecSystemDDLService.findSystemDDLListByKeyword("职位");
		request.setAttribute("postList", postList);
		List<ElecSystemDDL> jctList = elecSystemDDLService.findSystemDDLListByKeyword("所属单位");
		request.setAttribute("jctList", jctList);
		List<ElecSystemDDL> isDutyList = elecSystemDDLService.findSystemDDLListByKeyword("是否在职");
		request.setAttribute("isDutyList", isDutyList);
		
		//1、遍历role表	放入request中
		List<ElecRole> roleList = elecRoleService.findAllRoleList();
		request.setAttribute("roleList", roleList);
	}
	
	
	/**
	 * @Name: findJctUnit
	 * @Description: 实现 所属单位->单位名称 的二级联动
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-21 11:44:40
	 * @Parameters: 无
	 * @Return: String: 单位名称的 json数组
	 */
	public String findJctUnit(){
//		1：获取 所属单位
		String jctID = elecUser.getJctID();
		//2:得到 单位下面的 的所有单位名称
		List<ElecSystemDDL>  list = elecSystemDDLService.findSystemDDLListByKeyword(jctID);
		
		//3:将list 压入值栈顶
		//struts会自动将list转化成json数组
		ValueStackUtils.pushValueStack(list);
		
		return "findJctUnit";
	}
	
	
	/**
	 * @Name: checkUser
	 * @Description: 登录名的校验 
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-21 12:49:09
	 * @Parameters: 无
	 * @Return: String: 
			message
			1	登录名为空
			2	登录名已存在
			3	登录名不存在，可以使用
	 */
	@AnnotationLimit(mid="ff",pid="fa")
	public String checkUser(){
		String logonName = elecUser.getLogonName();
		String message = elecUserService.checkUserByLogonName(logonName);
		ValueStackUtils.pushValueStack(message);
		return "checkUser";
	}
	
	/**
	 * @Name: save
	 * @Description: 保存用户&更新用户
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-21 15:02:38
	 * @Parameters: elecUser
	 * @Return: String: 跳转到/close.jsp
	 */
	@AnnotationLimit(mid="fc",pid="fa")
	public String save(){
		elecUserService.saveUser(elecUser);
		
		/**2016-04-25 00:46:30 添加 --- roleflag
		 * 
		 * roleflag==null :系统管理员、此时保存-->close.jsp
		 * roleflag==1 :  非系统管理员， 保存    -->重定向到 /system/userEdit.jsp
		 * */
		String roleflag = elecUser.getRoleflag();
		if (roleflag!=null && roleflag.equals("1")) {
			return "redirectEdit";
		}
		
		return "save";
	}
	
	/**
	 * @Name: edit
	 * @Description: 跳转到编辑页面,并将用户信息 在表单上回显
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-21 16:52:27
	 * @Parameters: elecUser
	 * @Return: String: 跳转到/system/userEdit.jsp
	 */
	@AnnotationLimit(mid="fd",pid="fa")
	public String edit(){
		//获取用户
		String userID = elecUser.getUserID();
		ElecUser user = elecUserService.findUserByID(userID);
		
		//将viewfalg的VO对象转换成 PO持久层对象
		user.setViewflag(elecUser.getViewflag());
		user.setRoleflag(elecUser.getRoleflag());
		//将PO对象中的ElecRole().getRoleID() --> VO对象中的roleID
		user.setRoleID(user.getElecRole().getRoleID());		
		//将user压人值栈
		ValueStackUtils.pushValueStack(user);
		//加载数据字典
		this.initSystemDDL();
		
		//user 所属单位 和 单位名称的二级联动
		String ddlName = elecSystemDDLService.findDdlNameByKeywordAndDdlCode("所属单位", user.getJctID());
		List<ElecSystemDDL> jctUnitList = elecSystemDDLService.findSystemDDLListByKeyword(ddlName);
		request.setAttribute("jctUnitList", jctUnitList);
		
		return "edit";
	}
	
	
	/**
	 * @Name: download
	 * @Description: 文件下载
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-21 20:08:36
	 * @Parameters: elecUser
	 * @Return:	NONE
	 */
	public String download(){
		try {
			//根据文件ID 找到elecUserFile
			String fileID = elecUser.getFileID();
			ElecUserFile elecUserFile = elecUserService.findUserFileByID(fileID);
			
			//将文件放到InputStream中 
			String filepath = ServletActionContext.getServletContext().getRealPath("")+elecUserFile.getFileURL();
			InputStream inputStream = new FileInputStream(new File(filepath));
			
			String filename = new String(elecUserFile.getFileName().getBytes("GBK"),"iso8859-1");
			//将inputStream 写入到OutputStream中
			int bufferSize = 1024;
			response.setHeader("Content-disposion", "attachment;filename="+filename);
			response.setBufferSize(bufferSize);
			OutputStream outputStream = response.getOutputStream();
//			byte[] oBuff = new byte[bufferSize];
//			int isSize;
//			while((isSize=inputStream.read(oBuff)) != -1){
//				outputStream.write(oBuff, 0, isSize);
//			}
			for (int b; (b=inputStream.read())!=-1; ){
				outputStream.write(b);
			}
			
			inputStream.close();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return NONE;
	}
	
	
	/**
	 * @Name: 使用struts2的方式下载
	 * @Description: 文件下载
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-21 20:08:36
	 * @Parameters: elecUser
	 * @Return:	download
	 */
//	public String download(){
//		try {
//			//根据文件ID 找到elecUserFile
//			String fileID = elecUser.getFileID();
//			ElecUserFile elecUserFile = elecUserService.findUserFileByID(fileID);
//			
//			//将文件放到InputStream中 
//			String filepath = ServletActionContext.getServletContext().getRealPath("")+elecUserFile.getFileURL();
//			InputStream inputStream = new FileInputStream(new File(filepath));
//			
//			String filename = new String(elecUserFile.getFileName().getBytes("GBK"),"iso8859-1");
//			//将输入流压入到栈顶
//			elecUser.setInputStream(inputStream);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return "download";
//	}
	
	/**
	 * @Name: delete
	 * @Description: 用户删除
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-21 22:34:57
	 * @Parameters: 无
	 * @Return: String: 跳转到system/userIndex.jsp
	 */
	@AnnotationLimit(mid="fe",pid="fa")
	public String delete(){
		elecUserService.deleteUserByID(elecUser);
		
		/**
		 * 2016-04-28 01:25:12添加执行删除，传递当前页，这样做，可以删除后定向到当前页
		 */
		request.setAttribute("pageNO", request.getParameter("pageNO"));
		
		return "delete";
	}
	
}
