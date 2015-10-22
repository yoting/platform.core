package com.gusi.platform.core.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;

import com.gusi.platform.core.model.PageInfo;

public interface BaseDAO<T, ID extends Serializable> {
	/**
	 * 根据Id获得对象
	 * 
	 * @param id
	 * @return
	 */
	public T getObjById(ID id);

	/**
	 * 获得所有对象
	 * 
	 * @return
	 */
	public List<T> getObjList();

	/**
	 * 获得分页对象
	 * 
	 * @param pageInfo
	 * @return
	 */
	public PageInfo getObjListPaged(PageInfo pageInfo);

	/**
	 * 获得排序对象
	 * 
	 * @param sort
	 * @param order
	 * @return
	 */
	public List<T> getObjListSorted(String sort, Boolean order);

	/**
	 * 获得分页,且排序对象
	 * 
	 * @param pageInfo
	 * @param sort
	 * @param order
	 * @return
	 */
	public PageInfo getObjListPagedSorted(PageInfo pageInfo, String sort, Boolean order);

	/**
	 * 获得符合条件的对象
	 * 
	 * @param condition
	 * @return
	 */
	public List<T> getObjListByCondition(String condition);

	/**
	 * 获得分页符合条件的对象
	 * 
	 * @param condition
	 * @param sort
	 * @param order
	 * @return
	 */
	public List<T> getObjListSortedByCondition(String condition, String sort, Boolean order);

	/**
	 * 根据hql查询对象
	 * 
	 * @param hql
	 * @return
	 */
	public List<T> getObjListByHql(String hql);

	/**
	 * 根据criterial查询对象
	 * 
	 * @param criteria
	 * @return
	 */
	public List<T> getObjListByCriteria(Criteria criteria);

	public Serializable save(T obj);

	public void update(T obj);

	public void update(T obj, Boolean isUpdateTime);

	public void delete(T obj);

	public void delete(ID id);

	public void clear();

}
