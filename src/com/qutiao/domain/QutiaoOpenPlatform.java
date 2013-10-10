package com.qutiao.domain;

/**
 * QutiaoOpenPlatform entity. @author MyEclipse Persistence Tools
 */

public class QutiaoOpenPlatform implements java.io.Serializable {

	// Fields

	private Long opId;
	private Long userId;
	private String openId;
	private Integer openType;

	// Constructors

	/** default constructor */
	public QutiaoOpenPlatform() {
	}

	/** full constructor */
	public QutiaoOpenPlatform(Long userId, String openId, Integer openType) {
		this.userId = userId;
		this.openId = openId;
		this.openType = openType;
	}

	// Property accessors

	public Long getOpId() {
		return this.opId;
	}

	public void setOpId(Long opId) {
		this.opId = opId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getOpenType() {
		return this.openType;
	}

	public void setOpenType(Integer openType) {
		this.openType = openType;
	}

}