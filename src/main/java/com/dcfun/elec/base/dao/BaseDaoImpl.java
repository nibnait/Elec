package com.dcfun.elec.base.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dcfun.elec.utils.TUtils;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	Class entityClass = TUtils.getTClass(this.getClass());

	@Resource(name = "sessionFactory")
	public final void setSessionFactoryDi(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	// 更新
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	// 根据Id查
	public T findObjectById(Serializable id) {
		return (T) this.getHibernateTemplate().get(entityClass, id);
	}

	// 使用主键ID 删除一个或多个对象
	public void deleteObjectByIDs(Serializable... ids) {
		if (ids != null && ids.length > 0) {
			for (Serializable id : ids) {
				Object entity = this.findObjectById(id);
				if (entity != null) {
					this.getHibernateTemplate().delete(entity);
				}
			}
		}

	}

	@Override
	public void deleteObjectByCollection(List<T> list) {
		this.getHibernateTemplate().deleteAll(list);
	}

	@Override
	public List<T> findCollectionByConditionNoPage(
			final Map<String, Object> keyValues, Map<String, String> orderby) {

		final StringBuffer finalHql = new StringBuffer();
		finalHql.append("from " + entityClass.getSimpleName());
		finalHql.append(" where 1=1");
		if (keyValues != null) {
			for (Entry<String, Object> entry : keyValues.entrySet()) {// 把查询条件放到where的后面
				finalHql.append(" and " + entry.getKey() + "=:"
						+ entry.getKey());
			}
		}

		List<T> list = this.getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {

						Query query = session.createQuery(finalHql.toString());
						if (keyValues != null) {
							for (Entry<String, Object> entry : keyValues.entrySet()) {
							query.setParameter(entry.getKey(), entry.getValue());
							}
						}

						return query.list();
					}

				});

		return list;
	}

}
