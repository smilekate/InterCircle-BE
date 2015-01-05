package com.qianjin.intercircle.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.qianjin.intercircle.backend.validation.constraint.ExistName;

@Entity
@Table(name="BUser")
@ExistName
public class User extends SuperEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Length(min = 1, max = 45)
	private String name;
	
	private String nickname;
	
	@NotNull
	private Integer gender;
	
	private String password;
	
	private String email;

	@Id
	@GeneratedValue
	@Column(name="BUS_ID")
	@Override
	public Integer getId() {
		return id;
	}

	@Column(name="BUS_NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="BUS_NICKNAME")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name="BUS_GENDER")
	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	@Column(name="BUS_PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="BUS_EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
