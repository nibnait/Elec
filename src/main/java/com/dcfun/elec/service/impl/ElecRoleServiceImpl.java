package com.dcfun.elec.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dcfun.elec.dao.IElecPopedomDao;
import com.dcfun.elec.dao.IElecRoleDao;
import com.dcfun.elec.dao.IElecRolePopedomDao;
import com.dcfun.elec.dao.IElecUserDao;
import com.dcfun.elec.domain.ElecPopedom;
import com.dcfun.elec.domain.ElecRole;
import com.dcfun.elec.domain.ElecRolePopedom;
import com.dcfun.elec.service.IElecRoleService;


@Service(IElecRoleService.SERVICE_NAME)
@Transactional(readOnly=true)
public class ElecRoleServiceImpl implements IElecRoleService {

	@Resource(name=IElecRoleDao.SERVICE_NAME)
	IElecRoleDao elecRoleDao;
	
	@Resource(name=IElecPopedomDao.SERVICE_NAME)
	IElecPopedomDao elecPopedomDao;
	
	@Resource(name=IElecRolePopedomDao.SERVICE_NAME)
	IElecRolePopedomDao elecRolePopedomDao;
	
	@Resource(name=IElecUserDao.SERVICE_NAME)
	IElecUserDao elecUserDao;
	
	
	/**
	 * @Name: findAllRoleList
	 * @Description: 得到所有ElecRole
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-23 10:07:50
	 * @Parameters: 无
	 * @Return: List<ElecRole>
	 */
	public List<ElecRole> findAllRoleList() {
		Map<String, String> orderby = new LinkedHashMap<>();
		orderby.put("roleID", "asc");
		return elecRoleDao.findCollectionByConditionNoPage(null, orderby);
	}

	/**
	 * @Name: findAllPopedomList
	 * @Description: 得到所有ElecPopedom
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-23 10:07:50
	 * @Parameters: 无
	 * @Return: List<ElecPopedom>
	 */
	public List<ElecPopedom> findAllPopedomList() {
		
		Map<String, Object> condition = new HashMap<>();
		Map<String, String> orderby = new LinkedHashMap<>();
		
		condition.put("pid", "0");
		orderby.put("mid", "asc");
		List<ElecPopedom> parentPopedomList = elecPopedomDao.findCollectionByConditionNoPage(condition, orderby);
		
		if (parentPopedomList!=null && parentPopedomList.size()>0) {
			for(ElecPopedom parentPopedom: parentPopedomList){
				Map<String, Object> condition1 = new HashMap<>();
				Map<String, String> orderby1 = new LinkedHashMap<>();
				
				condition1.put("pid", parentPopedom.getMid());
				orderby1.put("mid", "asc");
				List<ElecPopedom> subPopedomList = elecPopedomDao.findCollectionByConditionNoPage(condition1, orderby1);
				parentPopedom.setList(subPopedomList);
			}
				
		}
		
		return parentPopedomList;
	}

	/**
	 * @Name: findAllPopedomListByRoleID
	 * @Description: 通过roleID得到此角色所具有的所有权限， 并标记在 allPopedomList中 
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-23 10:07:50
	 * @Parameters: 无
	 * @Return: List<ElecPopedom> allPopedomList
	 */
	public List<ElecPopedom> findAllPopedomListByRoleID(String roleID) {
		
		Map<String, Object> condition = new HashMap<>();
		condition.put("roleID", roleID);
		List<ElecRolePopedom> popedomList = elecRolePopedomDao.findCollectionByConditionNoPage(condition, null);
		
		List<ElecPopedom> allPopedomList = this.findAllPopedomList();
		
		// 使用包含（String.contains）标记allPopedomList
		// 组织midStr
		StringBuffer midStrBuf = new StringBuffer();
		if (popedomList != null && popedomList.size() > 0) {
			for (ElecRolePopedom elecRolePopedom : popedomList) {
				midStrBuf.append(elecRolePopedom.getMid() + "#");
			}
		}

		String midStr = midStrBuf.toString();

		// 下面开始 flag_allPopedomList
		this.flag_allPopedomList(midStr,allPopedomList);
		
		return allPopedomList;
	}

	private void flag_allPopedomList(
			String midStr, List<ElecPopedom> allPopedomList) {

		if (allPopedomList!=null && allPopedomList.size()>0) {
			for(ElecPopedom elecPopedom:allPopedomList){
				if (midStr.contains(elecPopedom.getMid())) {
					elecPopedom.setFlag("1");
				}else{
					elecPopedom.setFlag("2");
				}
				
				//**获取子集合!! 并标记
				List<ElecPopedom> childPoprdom = elecPopedom.getList();
				if (childPoprdom!=null && childPoprdom.size()>0) {
					this.flag_allPopedomList(midStr, childPoprdom);
				}
			}
		}
	}

	/**
	 * @Name: save
	 * @Description: 保存
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-23 19:51:15
	 * @Parameters: elecPopedom VO对象
	 * @Return: void
	 */
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void save(ElecPopedom elecPopedom) {

		//得到roleID 及其权限数组
		String roleID = elecPopedom.getRoleID();
		String[] popedoms = elecPopedom.getSelectoper();
	
		//根据roleID 清空rolePopedom表中原来的roleID对应的 popedoms
		Map<String, Object> condition = new HashMap<>();
		condition.put("roleID", roleID);
		List<ElecRolePopedom> list = elecRolePopedomDao.findCollectionByConditionNoPage(condition, null);
		elecRolePopedomDao.deleteObjectByCollection(list);
		
		//组织PO对象 保存
		//popedoms:0_am,am_an,am_ao
		for (int i = 0; i < popedoms.length; i++) {
			ElecRolePopedom elecRolePopedom = new ElecRolePopedom();
			elecRolePopedom.setRoleID(roleID);
			elecRolePopedom.setPid(popedoms[i].split("_")[0]);
			elecRolePopedom.setMid(popedoms[i].split("_")[1]);
			elecRolePopedomDao.save(elecRolePopedom);
		}
		
		
	}

	/**
	 * @Name: findPopedomByRoleID
	 * @Description:findPopedomByRoleID
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-24 13:57:00
	 * @Parameters: roleID
	 * @Return: popedomString
	 */
	public String findPopedomByRoleID(String roleID) {
		Map<String, Object> condition = new HashMap<>();
		condition.put("roleID", roleID);
		List<ElecRolePopedom> popedomList = elecRolePopedomDao.findCollectionByConditionNoPage(condition, null);
		
		List<ElecPopedom> allPopedomList = this.findAllPopedomList();
		
		// 使用包含（String.contains）标记allPopedomList
		// 组织midStr
		StringBuffer midStrBuf = new StringBuffer();
		if (popedomList != null && popedomList.size() > 0) {
			for (ElecRolePopedom elecRolePopedom : popedomList) {
				midStrBuf.append(elecRolePopedom.getMid() + "#");
			}
			midStrBuf.deleteCharAt(midStrBuf.length()-1);
		}

		String midStr = midStrBuf.toString();

		return midStr;
	}

	/**
	 * @Name: findPopedomByString
	 * @Description: findPopedomByString
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-24 13:57:05
	 * @Parameters: popedomString
	 * @Return: List<ElecPopedom>
	 */
	public List<ElecPopedom> findPopedomByString(String popedomStr) {

		Map<String, Object> condition = new HashMap<>();
		Map<String, String> orderby = new LinkedHashMap<>();
		condition.put("o.isMenu", true);
//		condition.put("o.mid in", "'"+popedom.replace("#", "','")+"'");
		orderby.put("o.mid", "asc");		
		
		List<ElecPopedom> popedomList = elecPopedomDao.findCollectionByConditionNoPage(condition, orderby);
		List<ElecPopedom> list = new ArrayList<>();
		
		//既然无法封装 in语句
		//就用笨方法吧
		if (popedomList!=null && popedomList.size()>0) {
			for(ElecPopedom elecPopedom: popedomList){
				if (popedomStr.contains(elecPopedom.getMid())) {
					list.add(elecPopedom);
				}
			}
		}
		
		return list;
	}

	/**
	 * @Name: findRolePopedomByID
	 * @Description: findRolePopedomByID
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-25 10:17:14
	 * @Parameters: String roleID, String mid, String pid
	 * @Return: boolean
	 */
	public boolean findRolePopedomByID(String roleID, String mid, String pid) {

		Map<String, Object> condition = new HashMap<>();
		condition.put("roleID", roleID);
		condition.put("mid", mid);
		condition.put("pid", pid);
		
		List<ElecRolePopedom> list = elecRolePopedomDao.findCollectionByConditionNoPage(condition, null);
		if (list!=null && list.size()>0) {
			return true;
		}
		
		return false;
	}
	
}
