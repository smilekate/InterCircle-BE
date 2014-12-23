package com.qianjin.intercircle.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BUser")
public class User extends SuperEntity {

	@Column(name="BUS_NAME")
	private String name;
	
	@Column(name="BUS_NICKNAME")
	private String nickname;
	
	@Column(name="BUS_GENDER")
	private int gender;
	
	@Column(name="BUS_PASSWORD")
	private String password;
	
	@Column(name="BUS_EMAIL")
	private String email;

	@Id
	@GeneratedValue
	@Column(name="BUS_ID")
	@Override
	public Integer getId() {
		return id;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
