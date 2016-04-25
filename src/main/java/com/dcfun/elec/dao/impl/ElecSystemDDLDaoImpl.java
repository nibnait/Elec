package com.dcfun.elec.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.dcfun.elec.base.dao.BaseDaoImpl;
import com.dcfun.elec.dao.IElecSystemDDLDao;
import com.dcfun.elec.dao.IElecTextDao;
import com.dcfun.elec.domain.ElecSystemDDL;
import com.dcfun.elec.domain.ElecText;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;


@Repository(IElecSystemDDLDao.SERVICE_NAME)
public class ElecSystemDDLDaoImpl extends BaseDaoImpl<ElecSystemDDL> implements IElecSystemDDLDao{

	/**  
	* @Name: findSystemDDLListByDistinct
	* @Description: 查询数据字典，去掉重复值
	* @Author: 刘洋（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-1（创建日期）
	* @Parameters: 无
	* @Return: List<ElecSystemDDL>：存放数据类型的集合
	*/
	public List<ElecSystemDDL> findSystemDDLListByDistinct() {

		String hql = "SELECT DISTINCT new ElecSystemDDL(keyword) FROM ElecSystemDDL";
		List<ElecSystemDDL> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	/**  
	* @Name: findDdlNameByKeywordAndDdlCode
	* @Description: 使用数据类型和数据项的编号，获取数据项的值
	* @Author: 刘洋（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-1（创建日期）
	* @Parameters: String keywrod,数据类型
	* 			   String ddlCode：数据项的编号
	* @Return: String：数据项的值
	*/
	public String findDdlNameByKeywordAndDdlCode(final String keyword, final String ddlCode) {
		final String hql = "select o.ddlName from ElecSystemDDL o where o.keyword=? and o.ddlCode=?";
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Object> list = this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setParameter(0, keyword);
				query.setParameter(1, Integer.parseInt(ddlCode));
				/**2016-04-25 18:13:02 添加 二级缓存机制*/
				query.setCacheable(true);
				return query.list();
			}
			
		});
		//返回数据项的值
		String ddlName = "";
		if(list!=null && list.size()>0){
			Object o = list.get(0);
			ddlName = o.toString();
		}
		return ddlName;
	}

}
