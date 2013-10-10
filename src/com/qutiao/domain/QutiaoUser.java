package com.qutiao.domain;

import java.sql.Timestamp;

/**
 * QutiaoUser entity. @author MyEclipse Persistence Tools
 */

public class QutiaoUser implements java.io.Serializable {

	// Fields

	private Long id;
	private String email;
	private String name;
	private String phone;
	private String qq;
	private String address;
	private Timestamp createTime;
	private Timestamp lastLoginTime;
	private Integer type;
	private Integer status;
	private String password;
	private String image;
	private String activeCode;
	private Boolean isActived;

	// Constructors

	/** default constructor */
	public QutiaoUser() {
	}

	/** minimal constructor */
	public QutiaoUser(String email, Timestamp createTime,
			Timestamp lastLoginTime) {
		this.email = email;
		this.createTime = createTime;
		this.lastLoginTime = lastLoginTime;
	}

	/** full constructor */
	public QutiaoUser(String email, String name, String phone, String qq,
			String address, Timestamp createTime, Timestamp lastLoginTime,
			Integer type, Integer status, String password, String image,
			String activeCode, Boolean isActived) {
		this.email = email;
		this.name = name;
		this.phone = phone;
		this.qq = qq;
		this.address = address;
		this.createTime = createTime;
		this.lastLoginTime = lastLoginTime;
		this.type = type;
		this.status = status;
		this.password = password;
		this.image = image;
		this.activeCode = activeCode;
		this.isActived = isActived;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getActiveCode() {
		return this.activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	public Boolean getIsActived() {
		return this.isActived;
	}

	public void setIsActived(Boolean isActived) {
		this.isActived = isActived;
	}

}