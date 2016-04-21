package com.dcfun.elec.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcfun.elec.dao.IElecUserDao;
import com.dcfun.elec.dao.IElecUserFileDao;
import com.dcfun.elec.domain.ElecUser;
import com.dcfun.elec.service.IElecUserService;
import com.dcfun.elec.utils.StringUtil;

@Service(IElecUserService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecUserServiceImpl implements IElecUserService {

	@Resource(name = IElecUserDao.SERVICE_NAME)
	IElecUserDao elecUserDao;

	@Resource(name = IElecUserFileDao.SERVICE_NAME)
	IElecUserFileDao elecUserFileDao;

	@Override
	public List<ElecUser> findCollectionByCondition(ElecUser elecUser) {

		Map<String, Object> condition = new HashMap<String, Object>();
		Map<String, String> orderby = new LinkedHashMap<String, String>();

		String userName = elecUser.getUserName();
		if (StringUtils.isNotBlank(userName)) {
			condition.put("userName like", "%"+userName+"%");
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
		
		
		//按入职时间 升序排序
		orderby.put("onDutyDateBegin", "asc");

		List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(
				condition, orderby);

		return list;
	}

}
