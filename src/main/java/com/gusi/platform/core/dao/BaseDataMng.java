package com.gusi.platform.core.dao;

import java.io.Serializable;
import java.util.List;

import com.gusi.platform.core.model.Root;

public interface BaseDataMng {
	public Root getObjById(Class clazz, Serializable id);

	public List getObjList(Class clazz);

	public List getObjListPaged(Class clazz, Integer firstRow, Integer pageSize);

	public List getObjListSorted(Class clazz, String sort, Boolean oredr);

	public List getObjListPagedSorted(Class clazz, Integer firstRow, Integer pageSize, String sort, Boolean order);

	public List getObjListByCondition(Class clazz, String condition);

	public List getObjListPagedSortedByCondition(Class clazz, String condition, Integer firstRow, Integer pageSize, String sort, Boolean order);

	public List getObjListByHql(String hql);

	public List getObjListBySql(String sql);

	public Serializable saveObj(Root obj);

	public void updateObj(Root obj);

	public void deleteObj(Root obj);

	public void deleteObj(Class clazz, Serializable id);
}
