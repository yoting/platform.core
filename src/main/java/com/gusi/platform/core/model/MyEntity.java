package com.gusi.platform.core.model;

import javax.persistence.Entity;

@Entity
public class MyEntity extends ItemIdLong {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
