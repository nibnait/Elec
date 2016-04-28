package com.dcfun.elec.base.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.dcfun.elec.domain.ElecFileUpload;
import com.dcfun.elec.domain.ElecUser;
import com.dcfun.elec.utils.page.PageInfo;

public interface IBaseDao<T> {
	
	void save(T entity);
	void update(T entity);
	T findObjectById(Serializable id);
	void deleteObjectByIDs(Serializable... ids);
	void deleteObjectByCollection(List<T> list);
	
	List<T> findCollectionByConditionNoPage(Map<String, Object> condition, Map<String, String> orderby);
	List<T> findCollectionByConditionNoPageWithCache(Map<String, Object> condition, Map<String, String> orderby);
//	List<T> findCollectionByConditionNoPageWithSql(Map<String, Object> condition, Map<String, String> orderby,	String scalar, ArrayList<String> innerJoin, String From);
	List<Object[]> findCollectionByConditionNoPageWithSql(Map<String, Object> condition, Map<String, String> orderby,	String scalar, ArrayList<String> innerJoin, String From);
	List<T> findCollectionByConditionWithPage(Map<String, Object> condition, Map<String, String> orderby,PageInfo pageInfo);
}
