package com.qutiao.domain;

import java.sql.Timestamp;

/**
 * QutiaoReview entity. @author MyEclipse Persistence Tools
 */

public class QutiaoReview implements java.io.Serializable {

	// Fields

	private Long id;
	private Long targetId;
	private Long pid;
	private Timestamp createTime;
	private Long agree;
	private Long disagree;
	private String content;
	private Integer status;
	private Long uid;

	// Constructors

	/** default constructor */
	public QutiaoReview() {
	}

	/** minimal constructor */
	public QutiaoReview(Timestamp createTime) {
		this.createTime = createTime;
	}

	/** full constructor */
	public QutiaoReview(Long targetId, Long pid, Timestamp createTime,
			Long agree, Long disagree, String content, Integer status, Long uid) {
		this.targetId = targetId;
		this.pid = pid;
		this.createTime = createTime;
		this.agree = agree;
		this.disagree = disagree;
		this.content = content;
		this.status = status;
		this.uid = uid;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTargetId() {
		return this.targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public Long getPid() {
		return this.pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Long getAgree() {
		return this.agree;
	}

	public void setAgree(Long agree) {
		this.agree = agree;
	}

	public Long getDisagree() {
		return this.disagree;
	}

	public void setDisagree(Long disagree) {
		this.disagree = disagree;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getUid() {
		return this.uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

}