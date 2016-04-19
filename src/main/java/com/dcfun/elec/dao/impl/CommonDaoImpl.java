package com.dcfun.elec.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dcfun.elec.dao.ICommonDao;
import com.dcfun.elec.utils.TUtils;

public class CommonDaoImpl<T> extends HibernateDaoSupport implements ICommonDao<T>{

	Class entityClass = TUtils.getTClass(this.getClass());
	
	@Resource(name="sessionFactory")
	public final void setSessionFactoryDi(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	public void save(T entity) {

		this.getHibernateTemplate().save(entity);
	}


	//更新
	public void update(T entity){
		this.getHibernateTemplate().update(entity);
	}

	//根据Id查
	public T findObjectById(Serializable id) {
		return (T) this.getHibernateTemplate().get(entityClass, id);
	}

	//使用主键ID 删除一个或多个对象
	public void deleteObjectByIDs(Serializable... ids) {
		if (ids!=null && ids.length>0) {
			for(Serializable id:ids){
				Object entity = this.findObjectById(id); 
				if (entity!=null) {
					this.getHibernateTemplate().delete(entity);
				}
			}
		}
		
	}

	@Override
	public void deleteObjectByCollection(List<T> list) {
		this.getHibernateTemplate().deleteAll(list);
	}

}
