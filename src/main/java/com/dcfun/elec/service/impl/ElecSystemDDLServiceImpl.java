package com.dcfun.elec.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dcfun.elec.dao.IElecSystemDDLDao;
import com.dcfun.elec.domain.ElecSystemDDL;
import com.dcfun.elec.service.IElecSystemDDLService;


@Service(IElecSystemDDLService.SERVICE_NAME)
@Transactional(readOnly=true)
public class ElecSystemDDLServiceImpl implements IElecSystemDDLService {

	@Resource(name=IElecSystemDDLDao.SERVICE_NAME)
	IElecSystemDDLDao elecSystemDDLDao;

	@Override
	public List<ElecSystemDDL> findCollectionByDistinct() {
		List<ElecSystemDDL> list = elecSystemDDLDao.findCollectionByDistinct();
		return list;
	}

	@Override
	public List<ElecSystemDDL> findCollectionByKeyword(String keyword) {
		
		Map<String, Object> condition = new HashMap<String, Object>();
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		
		condition.put("o.keyword", keyword);
		orderby.put("o.ddlCode", "asc");
		
		List<ElecSystemDDL> list = elecSystemDDLDao.findCollectionByConditionNoPage(condition, orderby);
		return list;
	}

	/**
	 * @Name: saveElecSystemDDL
	 * @Description: 编辑或新增数据字典类型
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-20 21:34:58
	 * @Parameters: elecSystemDDL模型驱动（typeFlag、keywordname、itemname）
	 * @Return: 无
	 */
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecSystemDDL(ElecSystemDDL elecSystemDDL) {
		
		String[] itemname = elecSystemDDL.getItemname();
		String keywordname = elecSystemDDL.getKeywordname();
		String typeFlag = elecSystemDDL.getTypeflag();
		
		//新增数据类型
		if (typeFlag!=null && typeFlag.equals("new")) {
			this.save(itemname,keywordname);
		}
		//编辑 新增
		else if(typeFlag!=null && typeFlag.equals("add")) {
			List<ElecSystemDDL> list = this.findCollectionByKeyword(keywordname);
			elecSystemDDLDao.deleteObjectByCollection(list);
			this.save(itemname, keywordname);
		}
		
		
	}

	private void save(String[] itemname, String keywordname) {
		for (int i = 0; i < itemname.length; i++) {
			ElecSystemDDL systemDDL = new ElecSystemDDL();
			systemDDL.setDdlCode(i+1);
			systemDDL.setDdlName(itemname[i]);
			systemDDL.setKeyword(keywordname);
			elecSystemDDLDao.save(systemDDL);
		}
		
	}

	
	
	
}
