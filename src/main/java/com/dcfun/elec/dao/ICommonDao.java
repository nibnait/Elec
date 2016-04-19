package com.dcfun.elec.dao;

import java.io.Serializable;
import java.util.List;

public interface ICommonDao<T> {
	
	void save(T entity);
	void update(T entity);
	T findObjectById(Serializable id);
	void deleteObjectByIDs(Serializable... ids);
	void deleteObjectByCollection(List<T> list);
	
	
}
