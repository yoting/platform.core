package com.gusi.platform.core.dao;

import java.io.Serializable;

public interface BaseDataMngGen<T> {
	public <T> T getObjById(T type, Serializable id);

	public <T> void save(T obj);

}
