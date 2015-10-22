package com.gusi.platform.core.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.gusi.platform.core.dao.BaseDataMngGen;

//@Repository
public class BaseDataMngGenImpl<T> implements BaseDataMngGen<T> {

	// @Autowired
	HibernateTemplate hibernateTemplate;

	public <T> void save(T obj) {

		hibernateTemplate.save(obj);
	}

	public <T> T getObjById(T type, Serializable id) {
		List objList = hibernateTemplate.find("from User where id =" + id);

		return null;
	}

}
