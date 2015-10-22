package com.gusi.platform.core.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.gusi.platform.core.dao.BaseDataMng;
import com.gusi.platform.core.model.Root;

@Repository
public class BaseDataMngImpl implements BaseDataMng {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	private Session getSession() {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		return session;
	}

	public Root getObjById(Class clazz, Serializable id) {
		Root obj = hibernateTemplate.get(clazz, id);
		return obj;
	}

	public List<Root> getObjList(Class clazz) {
		return getObjListPagedSortedByCondition(clazz, null, null, null, null, null);
	}

	public List<Root> getObjListPaged(Class clazz, final Integer firstRow, final Integer pageSize) {
		return getObjListPagedSortedByCondition(clazz, null, firstRow, pageSize, null, null);
	}

	public List<Root> getObjListSorted(Class clazz, String sort, Boolean order) {
		return getObjListPagedSortedByCondition(clazz, null, null, null, sort, order);
	}

	public List<Root> getObjListPagedSorted(Class clazz, final Integer firstRow, final Integer pageSize, String sort, Boolean order) {
		return getObjListPagedSortedByCondition(clazz, null, firstRow, pageSize, sort, order);
	}

	public List<Root> getObjListByCondition(Class clazz, String condition) {
		return getObjListPagedSortedByCondition(clazz, condition, null, null, null, null);
	}

	public List<Root> getObjListPagedSortedByCondition(Class clazz, String condition, final Integer firstRow, final Integer pageSize, String sort, Boolean order) {
		String hql = "from " + clazz.getSimpleName() + " as obj where 1=1 ";
		if (condition != null && !condition.isEmpty()) {
			hql += " and " + condition;
		}
		if (sort != null && !sort.isEmpty()) {
			hql += " order by " + sort;
		} else {
			hql += " order by id";
		}
		if (order != null && order) {
			hql += " desc";
		} else {
			hql += " asc";
		}
		final String hqlFinal = hql;
		List<Root> objList = new ArrayList<Root>();
		if (firstRow != null && pageSize != null && pageSize != -1) {
			objList = hibernateTemplate.executeFind(new HibernateCallback<List<Root>>() {
				public List<Root> doInHibernate(Session session) throws HibernateException, SQLException {
					Query query = session.createQuery(hqlFinal);
					query.setFirstResult(firstRow);
					query.setMaxResults(pageSize);
					return query.list();
				}
			});
		} else {
			objList = hibernateTemplate.find(hql);
		}
		return objList;
	}

	public List<Root> getObjListByHql(String hql) {
		List<Root> objList = hibernateTemplate.find(hql);
		return objList;
	}

	public List<Root> getObjListBySql(final String sql) {
		List<Root> objList = hibernateTemplate.executeFind(new HibernateCallback<List<Root>>() {
			public List<Root> doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
		return objList;
	}

	public Serializable saveObj(Root obj) {
		obj.setCreateDate(new Date());
		obj.setUpdateDate(new Date());
		Serializable id = hibernateTemplate.save(obj);
		return id;
	}

	public void updateObj(Root obj) {
		obj.setUpdateDate(new Date());
		hibernateTemplate.update(obj);
	}

	public void deleteObj(Root obj) {
		hibernateTemplate.delete(obj);
	}

	public void deleteObj(Class clazz, Serializable id) {
		Root obj = getObjById(clazz, id);
		deleteObj(obj);
	}

}
