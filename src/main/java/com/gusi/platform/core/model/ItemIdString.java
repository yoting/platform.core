package com.gusi.platform.core.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ItemIdString {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
