package com.qutiao.domain;

/**
 * QutiaoProductKeywordRelation entity. @author MyEclipse Persistence Tools
 */

public class QutiaoProductKeywordRelation implements java.io.Serializable {

	// Fields

	private Long id;
	private Long pid;
	private Long kid;
	private Integer status;

	// Constructors

	/** default constructor */
	public QutiaoProductKeywordRelation() {
	}

	/** full constructor */
	public QutiaoProductKeywordRelation(Long pid, Long kid, Integer status) {
		this.pid = pid;
		this.kid = kid;
		this.status = status;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return this.pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getKid() {
		return this.kid;
	}

	public void setKid(Long kid) {
		this.kid = kid;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}