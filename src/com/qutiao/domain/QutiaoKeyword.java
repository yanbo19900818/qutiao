package com.qutiao.domain;

/**
 * QutiaoKeyword entity. @author MyEclipse Persistence Tools
 */

public class QutiaoKeyword implements java.io.Serializable {

	// Fields

	private Long id;
	private String word;
	private Integer status;

	// Constructors

	/** default constructor */
	public QutiaoKeyword() {
	}

	/** full constructor */
	public QutiaoKeyword(String word, Integer status) {
		this.word = word;
		this.status = status;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}