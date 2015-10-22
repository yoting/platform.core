package com.gusi.platform.core.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateTemplateMng {

	private static Logger logger = LoggerFactory.getLogger(HibernateTemplateMng.class);

	private static HibernateTemplateMng instance = null;

	private HibernateTemplateMng() {
	}

	private static HibernateTemplateMng getInstance() {
		if (instance == null) {
			instance = new HibernateTemplateMng();
		}
		return instance;
	}
}
