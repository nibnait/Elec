package com.dcfun.elec.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dcfun.elec.dao.IElecCommonMsgDao;
import com.dcfun.elec.dao.IElecTextDao;
import com.dcfun.elec.domain.ElecCommonMsg;
import com.dcfun.elec.domain.ElecText;
import com.dcfun.elec.service.IElecCommonMsgService;
import com.dcfun.elec.service.IElecTextService;


@Service(IElecCommonMsgService.SERVICE_NAME)
@Transactional(readOnly=true)
public class ElecCommonMsgServiceImpl implements IElecCommonMsgService {

	@Resource(name=IElecCommonMsgDao.SERVICE_NAME)
	IElecCommonMsgDao elecCommonMsgDao;

	/**
	 * @Name: findCommonMsg
	 * @Description: 查询Elec_CommonMsg（运行监控表）的数据
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-19 14:52:12
	 * @Parameters: 无
	 * @Return: String: ElecCommonMsg:返回PO对象（运行监控的数据）
	 */
	public ElecCommonMsg findCommonMsg() {
		List<ElecCommonMsg> list = elecCommonMsgDao.findCollectionByConditionNoPage(null, null);
		//返回惟一对象
		ElecCommonMsg elecCommonMsg = null;
		if(list!=null && list.size()>0){
			elecCommonMsg = list.get(0);
		}
		return elecCommonMsg;
	}

	/**  
	* @Name: saveElecCommonMsg
	* @Description: 保存运行监控
	* @Author: 刘洋（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 
	* @Parameters: ElecCommonMsg：封装保存的参数
	* @Return: 无
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecCommonMsg(ElecCommonMsg elecCommonMsg) {
		
		List<ElecCommonMsg> list = elecCommonMsgDao.findCollectionByConditionNoPage(null, null);

		//如果里面有数据 、则更新数据库
		if(list!=null && list.size()>0){
			//使用 Hibernate快照更新！
			// 若用this.getHibernateTemplate.update()  更新 则会在同一个session中产生 两个相同OID
			ElecCommonMsg commonMsg = list.get(0);
			commonMsg.setCreateDate(new Date());
			commonMsg.setDevRun(elecCommonMsg.getDevRun());
			commonMsg.setStationRun(elecCommonMsg.getStationRun());
		}
		//如果里面没有 则保存
		else{
			elecCommonMsg.setCreateDate(new Date());
			elecCommonMsgDao.save(elecCommonMsg);
		}
	}


}
