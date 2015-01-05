package com.qianjin.intercircle.backend.entity;

import java.io.Serializable;

public abstract class SuperEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Integer id;
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public abstract Integer getId();
}
