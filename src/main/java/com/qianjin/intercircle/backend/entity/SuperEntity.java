package com.qianjin.intercircle.backend.entity;

public abstract class SuperEntity {

	protected Integer id;
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public abstract Integer getId();
}
