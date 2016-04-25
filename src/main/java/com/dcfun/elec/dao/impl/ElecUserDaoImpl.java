package com.dcfun.elec.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.dcfun.elec.base.dao.BaseDaoImpl;
import com.dcfun.elec.dao.IElecUserDao;
import com.dcfun.elec.domain.ElecUser;


@Repository(IElecUserDao.SERVICE_NAME)
public class ElecUserDaoImpl extends BaseDaoImpl<ElecUser> implements IElecUserDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<ElecUser> findCollectionByConditionNoPageWithSql(
			final Map<String, Object> keyValues, Map<String, String> orderby) {

		final StringBuffer finalSql = new StringBuffer();
		finalSql.append("SELECT o.userID,o.logonName,o.userName,a.ddlName,o.contactTel,o.onDutyDate,b.ddlName " +
					 " FROM elec_user o " + 
					 " INNER JOIN elec_systemddl a ON o.sexID = a.ddlCode AND a.keyword = '性别' " +
					 " INNER JOIN elec_systemddl b ON o.postID= b.ddlCode AND b.keyword = '职位' " +
					 " WHERE 1=1");
		finalSql.append(this.buildWhere(keyValues));
		finalSql.append(this.buildOrderBy(orderby));
		List<Object[]> list = this.getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {

						//当投影查询的字段用到相同名称的字段，为了区分字段，使用标量查询
						Query query = session.createSQLQuery(finalSql.toString())
											.addScalar("o.userID")
											.addScalar("o.logonName")
											.addScalar("o.userName")
											.addScalar("a.ddlName")
											.addScalar("o.contactTel")
											.addScalar("o.onDutyDate")
											.addScalar("b.ddlName");
						if (keyValues != null) {
							for (Entry<String, Object> entry : keyValues.entrySet()) {
								
								/**2016-04-25 18:36:11 灵机一动
								 * 
								 * 		entry.getValue() 中加个 "("...")"不就可以定位到了么。。。哎、蠢！
								 * */
//								if(entry.getKey().contains("in")){
//									/**2016-04-24 14:02:04 添加 --- 封装带in的查询条件*/
//									if (entry.getKey().contains(".")) {
//										query.setParameter(entry.getKey().split("\\.")[1].substring(0, entry.getKey().length()-4), entry.getValue());
//									}else {
//										query.setParameter(entry.getKey().substring(0, entry.getKey().length()-3), entry.getValue());
//									}
//								}else
								if (entry.getKey().contains("like")) {
									
									if(entry.getKey().contains(".")){
										
										query.setParameter(entry.getKey().split("\\.")[1].substring(0, entry.getKey().length()-5), entry.getValue());
									}else{
										//最简单的情况，没有"."的  “>=”、“<=” 
										//为了支持 >=、<=  也是拼 [笑Cry]
 										query.setParameter(entry.getKey().substring(0, entry.getKey().length()-5), "'"+entry.getValue());
									}
									
								}else if(entry.getKey().contains("=")){
									
									if(entry.getKey().contains(".")){
										query.setParameter(entry.getKey().split("\\.")[1].split("\\=")[0].substring(0, entry.getKey().length()-3), entry.getValue());
									}else{
										//最简单的情况，没有"."的  “>=”、“<=” 
										//为了支持 >=、<=  也是拼 [笑Cry]
										query.setParameter(entry.getKey().split("\\=")[0].substring(0, entry.getKey().length()-3), entry.getValue());
									}
									
								}else if(entry.getKey().contains(".")){
									query.setParameter(entry.getKey().split("\\.")[1], entry.getValue());
								}else{
									query.setParameter(entry.getKey(), entry.getValue());
								}
								
							}
						}

						return query.list();
					}

				});
		
		//将List<Object[]>转换成List<ElecUser>
		List<ElecUser> userList = new ArrayList<ElecUser>();
		if(list!=null && list.size()>0){
			for(Object [] o: list){
				ElecUser elecUser = new ElecUser();
				int cnt = 0;
				//o.userID,o.logonName,o.userName,a.ddlName,o.contactTel,o.onDutyDate,b.ddlName
				elecUser.setUserID(o[cnt++].toString());
				elecUser.setLogonName(o[cnt++].toString());
				elecUser.setUserName(o[cnt++].toString());
				elecUser.setSexID(o[cnt++].toString());
				elecUser.setContactTel(o[cnt++].toString());
				elecUser.setOnDutyDate((Date)o[cnt++]);
				elecUser.setPostID(o[cnt++].toString());
				userList.add(elecUser);
			}
		}
		return userList;
	}

	private String buildOrderBy(Map<String, String> orderby) {
		StringBuffer buffer = new StringBuffer("");
		if(orderby!=null && orderby.size()>0){
			buffer.append(" ORDER BY ");
			for(Map.Entry<String, String> map:orderby.entrySet()){
				buffer.append(map.getKey()+" "+map.getValue()+",");
			}
			//在循环后，删除最后一个逗号
			buffer.deleteCharAt(buffer.length()-1);
		}
		return buffer.toString();
	}

	private StringBuffer buildWhere(Map<String, Object> keyValues) {
		final StringBuffer finalHql = new StringBuffer();
		if (keyValues != null) {
			
			for (Entry<String, Object> entry : keyValues.entrySet()) {// 把查询条件放到where的后面
				
//				if(entry.getKey().contains("in")){
//					/**2016-04-24 14:02:04 添加 --- 封装带in的查询条件*/hibernate 找不到in(:mid) mid!!! 
//					if (entry.getKey().contains(".")) {				//entry.getKey().split("\\.")[1].substring(0, entry.getKey().length()-4)
//						System.out.println("finalHql.append:"+entry.getKey().split("\\.")[1].substring(0, entry.getKey().split("\\.")[1].length()-3));
//						finalHql.append(" and "+entry.getKey()+" :"+entry.getKey().split("\\.")[1].substring(0, entry.getKey().split("\\.")[1].length()-3));
//						System.out.println("finalHql = "+finalHql);
//					}else {
//						//先写不带"." 的，  Mid in ( :Mid)
//						finalHql.append(" and "+entry.getKey()+"(:"+entry.getKey().substring(0, entry.getKey().length()-3)+" )");
//					}
//				}else 
					if (entry.getKey().contains("like")) {
					if(entry.getKey().contains(".")){
						finalHql.append(" and "+entry.getKey()+":"+entry.getKey().split("\\.")[1].substring(0, entry.getKey().length()-5));//用了两个split 只为把“>”、“<”分出来
					}else{
						//最简单的情况，没有"."的  “like” 
						
						finalHql.append(" and "+entry.getKey()+":"+entry.getKey().substring(0, entry.getKey().length()-5));
					}
					
				}else if(entry.getKey().contains("=")){
					
					if(entry.getKey().contains(".")){
						finalHql.append(" and "+entry.getKey().split("\\.")[1].split("\\=")[0]+"= :"+entry.getKey().split("\\.")[1].split("\\=")[0].substring(0, entry.getKey().length()-4));//用了两个split 只为把“>”、“<”分出来
					}else{
						//最简单的情况，没有"."的  “>=”、“<=” 
						finalHql.append(" and "+entry.getKey().split("\\=")[0]+"= :"+entry.getKey().split("\\=")[0].substring(0, entry.getKey().length()-4));
					}
					
					
				}else if(entry.getKey().contains(".")){
					finalHql.append(" and "+entry.getKey()+"=:"+entry.getKey().split("\\.")[1]);
				}else{
					finalHql.append(" and "+entry.getKey()+"=:"+entry.getKey());
				}
			}
		}
		return finalHql;
	}


}
