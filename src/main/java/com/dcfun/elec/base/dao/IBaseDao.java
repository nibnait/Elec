package com.dcfun.elec.base.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IBaseDao<T> {
	
	void save(T entity);
	void update(T entity);
	T findObjectById(Serializable id);
	void deleteObjectByIDs(Serializable... ids);
	void deleteObjectByCollection(List<T> list);
	
	List<T> findCollectionByConditionNoPage(Map<String, Object> condition, Map<String, String> orderby);
}
