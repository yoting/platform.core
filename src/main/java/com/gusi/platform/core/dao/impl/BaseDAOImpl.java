package com.gusi.platform.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gusi.platform.core.dao.BaseDAO;
import com.gusi.platform.core.model.PageInfo;
import com.gusi.platform.core.model.Root;

//@Repository
@SuppressWarnings("unchecked")
public class BaseDAOImpl<T extends Root, ID extends Serializable> implements BaseDAO<T, ID> {

	protected Class<T> entityClass;

	@Autowired
	protected SessionFactory sf;

	public BaseDAOImpl() {
		getEntityClass();
	}

	protected Class<T> getEntityClass() {
		if (entityClass == null) {
			Type type = this.getClass().getGenericSuperclass();
			entityClass = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
		}
		return entityClass;
	}

	protected Session getSession() {
		Session session = sf.getCurrentSession();
		return session;
	}

	public T getObjById(ID id) {
		T obj = (T) getSession().get(entityClass, id);
		return obj;
	}

	public List<T> getObjList() {
		return getObjListByCondition(null);
	}

	public PageInfo getObjListPaged(PageInfo pageInfo) {
		return getObjListPagedSorted(pageInfo, null, null);
	}

	public List<T> getObjListSorted(String sort, Boolean order) {
		return getObjListSortedByCondition(null, sort, order);
	}

	public PageInfo getObjListPagedSorted(PageInfo pageInfo, String sort, Boolean order) {

		Integer firstRow = pageInfo.getFirstRow();
		Integer pageSize = pageInfo.getPageSize();
		String condition = pageInfo.getCondition();
		String hql = "from " + entityClass.getSimpleName() + " as obj where 1=1 ";
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

		Query query = getSession().createQuery(hql);
		pageInfo.setTotalRecords(query.list().size());
		if (firstRow != null && pageSize != null & pageSize != -1) {
			query.setFirstResult(firstRow);
			query.setMaxResults(pageSize);
		}
		pageInfo.setObjList(query.list());

		return pageInfo;
	}

	public List<T> getObjListByCondition(String condition) {

		return getObjListSortedByCondition(condition, null, null);
	}

	public List<T> getObjListSortedByCondition(String condition, String sort, Boolean order) {
		String hql = "from " + entityClass.getSimpleName() + " as obj where 1=1 ";
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
		return getObjListByHql(hql);
	}

	public List<T> getObjListByHql(String hql) {
		List<T> objList = null;
		Query query = getSession().createQuery(hql);
		objList = query.list();
		return objList;
	}

	public List<T> getObjListByCriteria(Criteria criteria) {
		List<T> objList = null;
		objList = criteria.list();
		return objList;
	}

	public Serializable save(T obj) {
		obj.setCreateDate(new Date());
		obj.setUpdateDate(new Date());
		return getSession().save(obj);
	}

	public void update(T obj) {
		update(obj, null);
	}

	/**
	 * 更新对象，isUpdateTime表示是否更新更新时间，默认的null表示更新，true表示更新，只有是false表示不更新
	 */
	public void update(T obj, Boolean isUpdateTime) {
		if (isUpdateTime == null || isUpdateTime) {
			obj.setUpdateDate(new Date());
		}
		clear();
		getSession().update(obj);
	}

	public void delete(T obj) {
		getSession().delete(obj);
	}

	public void delete(ID id) {
		T obj = (T) getSession().get(entityClass, id);
		delete(obj);
	}

	public void clear() {
		getSession().clear();
	}

}
