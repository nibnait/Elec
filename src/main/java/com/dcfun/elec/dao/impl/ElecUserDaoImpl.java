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

}
