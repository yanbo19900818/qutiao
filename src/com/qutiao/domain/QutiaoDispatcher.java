package com.qutiao.domain;

/**
 * QutiaoDispatcher entity. @author MyEclipse Persistence Tools
 */

public class QutiaoDispatcher implements java.io.Serializable {

	// Fields

	private Long id;
	private String phoneUrl;
	private String webUrl;
	private String originalUrl;

	// Constructors

	/** default constructor */
	public QutiaoDispatcher() {
	}

	/** full constructor */
	public QutiaoDispatcher(String phoneUrl, String webUrl, String originalUrl) {
		this.phoneUrl = phoneUrl;
		this.webUrl = webUrl;
		this.originalUrl = originalUrl;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhoneUrl() {
		return this.phoneUrl;
	}

	public void setPhoneUrl(String phoneUrl) {
		this.phoneUrl = phoneUrl;
	}

	public String getWebUrl() {
		return this.webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getOriginalUrl() {
		return this.originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

}