package com.gusi.platform.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gusi.platform.core.dao.BaseDAO;
import com.gusi.platform.core.model.MyEntity;

@Service
@Transactional
public class MyEntityService {

	// @Resource(name = "baseDAOImpl")
	BaseDAO<MyEntity, Long> entityDao;

	public void save(MyEntity entity) {
		entityDao.save(entity);
	}

	public MyEntity get() {
		return entityDao.getObjById(1L);
	}

}
