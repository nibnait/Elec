package com.dcfun.elec.service.impl;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dcfun.elec.dao.IElecRoleDao;
import com.dcfun.elec.dao.IElecSystemDDLDao;
import com.dcfun.elec.dao.IElecUserDao;
import com.dcfun.elec.dao.IElecUserFileDao;
import com.dcfun.elec.domain.ElecRole;
import com.dcfun.elec.domain.ElecUser;
import com.dcfun.elec.domain.ElecUserFile;
import com.dcfun.elec.service.IElecUserService;
import com.dcfun.elec.utils.MD5keyBean;
import com.dcfun.elec.utils.FileUploadUtils;

@Service(IElecUserService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecUserServiceImpl implements IElecUserService {

	@Resource(name = IElecUserDao.SERVICE_NAME)
	IElecUserDao elecUserDao;

	@Resource(name = IElecUserFileDao.SERVICE_NAME)
	IElecUserFileDao elecUserFileDao;

	/** 数据字典表Dao */
	@Resource(name = IElecSystemDDLDao.SERVICE_NAME)
	IElecSystemDDLDao elecSystemDDLDao;
	
	@Resource(name = IElecRoleDao.SERVICE_NAME)
	IElecRoleDao elecRoleDao;

	@Override
	public List<ElecUser> findUserListByCondition(ElecUser elecUser) {

		Map<String, Object> condition = new HashMap<String, Object>();
		Map<String, String> orderby = new LinkedHashMap<String, String>();

		String userName = elecUser.getUserName();
		if (StringUtils.isNotBlank(userName)) {
			condition.put("userName like", "%" + userName + "%");
		}

		String jctID = elecUser.getJctID();
		if (StringUtils.isNotBlank(jctID)) {
			condition.put("jctID", jctID);
		}

		// 入职时间
		Date onDutyDateBegin = elecUser.getOnDutyDateBegin();
		if (onDutyDateBegin != null) {
			condition.put("onDutyDate >=", onDutyDateBegin);
		}

		// 离职时间
		Date onDutyDateEnd = elecUser.getOnDutyDateEnd();
		if (onDutyDateEnd != null) {
			condition.put("offDutyDate <=", onDutyDateEnd);
		}

		// 按入职时间 升序排序
		orderby.put("onDutyDate", "asc");

		List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(
				condition, orderby);

		this.convertSystemDDL(list);
		return list;
	}

	/** 使用数据类型和数据项的编号，查询数据字典，获取数据项的值 */
	private void convertSystemDDL(List<ElecUser> list) {
		if (list != null && list.size() > 0) {
			for (ElecUser user : list) {
				// 性别
				if (user.getSexID() != null) {
					String sexID = elecSystemDDLDao
							.findDdlNameByKeywordAndDdlCode("性别",
									user.getSexID());
					user.setSexID(sexID);
				}
				// 职位
				if (user.getPostID() != null) {
					String postID = elecSystemDDLDao
							.findDdlNameByKeywordAndDdlCode("职位",
									user.getPostID());
					user.setPostID(postID);
				}
			}
		}
	}

	/**
	 * @Name: checkUserByLogonName
	 * @Description: 登录名的校验
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-21 12:49:09
	 * @Parameters: 无
	 * @Return: String: message 1 登录名为空 2 登录名已存在 3 登录名不存在，可以使用
	 */
	public String checkUserByLogonName(String logonName) {
		String message = "";

		if (StringUtils.isNotBlank(logonName)) {
			Map<String, Object> condition = new HashMap<>();
			condition.put("logonName", logonName);
			List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(
					condition, null);
			if (list != null && list.size() > 0) {
				message = "2";
			} else {
				message = "3";
			}
		} else {
			message = "1";
		}

		return message;
	}

	/**
	 * @Name: saveUser
	 * @Description: 保存用户 &更新
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-21 15:02:38
	 * @Parameters: elecUser
	 * @Return: void
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveUser(ElecUser elecUser) {
		
		/**2016-04-24 01:15:18 添加 elecUser.setRole*/
		ElecRole role = elecRoleDao.findObjectById(elecUser.getRoleID());
		elecUser.setElecRole(role);
		
		this.saveFiles(elecUser);

		this.md5Password(elecUser);

		if (elecUser.getUserID() != null) {
			elecUserDao.update(elecUser); // 更新
		} else {
			elecUserDao.save(elecUser); // userID为空 则是新的用户->保存
		}

	}

	/**
	 * 加密： 密码为空 --> 设置初始密码：123456、不为空 、 修改密码了 
	 * 编辑的时候 若未修改密码，则无需再次加密了！！！
	 *
	 * 
	 * 妈蛋 被绕进去了，人家 正规的时候 修改密码 会另外做一条链接(或者请求、或者action)
	 * 这种情况 就是个鸡肋！还要管理员 手动修改密码？呵呵 
	 * 
	 * @param elecUser
	 */
	private void md5Password(ElecUser elecUser) {
		// 获取加密前的密码
		String logonPwd = elecUser.getLogonPwd();
		// 加密后的密码
		String md5password = "";
		// 如果没有填写密码，设置初始密码为123456
		if (StringUtils.isBlank(logonPwd)) {
			logonPwd = "123456";
		}
		// 获取修改用户之前的密码
		String password = elecUser.getPassword();
		// 编辑的时候，没有修改密码的时候，是不需要加密的
		if (password != null && password.equals(logonPwd)) {
			md5password = logonPwd;
		}
		// 新增的时候，或者是编辑的时候修改密码的时候，需要加密的
		else {
			// 使用md5加密的时候
			md5password = MD5keyBean.getkeyBeanofStr(logonPwd);
		}
		// 放置到ElecUser对象中
		elecUser.setLogonPwd(md5password);
	}

	// 文件上传
	private void saveFiles(ElecUser elecUser) {
		// 1、得到上传文件
		File[] uploads = elecUser.getUploads();
		String[] fileName = elecUser.getUploadsFileName();
		String[] fileContentType = elecUser.getUploadsContentType();

		// 一个一个 传到服务器指定路径

		if (uploads != null && uploads.length > 0) {
			for (int i = 0; i < fileContentType.length; i++) {
				ElecUserFile elecUserFile = new ElecUserFile();
				elecUserFile.setElecUser(elecUser);
				elecUserFile.setFileName(fileName[i]);
				String fileURL = FileUploadUtils.uploadFiles(uploads[i],
						fileName[i], "用户管理");
				elecUserFile.setFileURL(fileURL);
				elecUserFile.setProgressTime(new Date());
				elecUserFileDao.save(elecUserFile);
			}
		}
	}

	/**
	 * @Name: findUserByID
	 * @Description: findUserByID
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-21 15:02:38
	 * @Parameters: userID
	 * @Return: ElecUser
	 */
	public ElecUser findUserByID(String userID) {
		return elecUserDao.findObjectById(userID);
	}

	/**
	 * @Name: findUserFileByID
	 * @Description: findUserByID
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-21 20:12:24
	 * @Parameters: fileID
	 * @Return: ElecUserFile
	 */
	public ElecUserFile findUserFileByID(String fileID) {
		return elecUserFileDao.findObjectById(fileID);
	}

	/**
	 * @Name: deleteUserByID
	 * @Description: 用户删除
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-21 22:34:57
	 * @Parameters: elecUser
	 * @Return: NONE
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteUserByID(ElecUser elecUser) {

		// 1、得到要删除的userIDs
		String userID = elecUser.getUserID();
		String[] userIDs = userID.split(", ");

		// 2、删除服务器中的 用户文件
		if (userIDs != null && userIDs.length > 0) {

			for (int i = 0; i < userIDs.length; i++) {
				// 通过用户ID 得到用户 --> 得到每个用户的userFile集合
				ElecUser user = elecUserDao.findObjectById(userIDs[i]);
				Set<ElecUserFile> userFiles = user.getElecUserFiles();

				if (userFiles != null && userFiles.size() > 0) {
					for (ElecUserFile userFile : userFiles) {
						String filepath = ServletActionContext
								.getServletContext().getRealPath("")
								+ userFile.getFileURL();
						File file = new File(filepath);
						if (file.exists()) {
							file.delete();
						}

					}
				}

			}
		}

		// 3、删除用户表 和用户文件表中的数据（级联操作）
		elecUserDao.deleteObjectByIDs(userIDs);

	}

	/**
	 * @Name: findUserByLogonName
	 * @Description: findUserByLogonName
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-21 15:02:38
	 * @Parameters: logonName
	 * @Return: ElecUser
	 */
	public ElecUser findUserByLogonName(String logonName) {
		Map<String, Object> condition = new HashMap<>();
		condition.put("logonName", logonName);
		List<ElecUser> userList = elecUserDao.findCollectionByConditionNoPage(condition, null);
		
		ElecUser elecUser = null;
		if (userList!=null && userList.size()>0) {
			elecUser = userList.get(0);
		}

		return elecUser;
	}

}
