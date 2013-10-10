package com.qutiao.domain;

import java.sql.Timestamp;

import com.qutiao.crawler.bean.InfoBean;
import com.qutiao.util.QutiaoConstant;
import com.qutiao.util.QutiaoProductParser;
import com.qutiao.util.url.UrlConvertTool;

/**
 * QutiaoProduct entity. @author MyEclipse Persistence Tools
 */

public class QutiaoProduct implements java.io.Serializable {

	// Fields

	private Long id;
	private Timestamp createTime;
	private Timestamp modifyTime;
	private String url;
	private Long agree;
	private Long disagree;
	private String content;
	private String title;
	private String image;
	private Integer status;
	private String description;
	private Long topicId;

	// Constructors

	/** default constructor */
	public QutiaoProduct() {
	}

	public QutiaoProduct(InfoBean infoBean) {
		this.topicId = infoBean.getTopic_id();
		this.createTime = new Timestamp(infoBean.getCreate_time());
		this.modifyTime = new Timestamp(infoBean.getCreate_time());
		this.url = infoBean.getLink();
		this.agree = 0L;
		this.disagree = 0L;
		this.content = QutiaoProductParser
				.convertInfoBeanToProductContent(infoBean);
		this.title = infoBean.getTitle();
		this.image = QutiaoProductParser
				.convertInfoBeanToProductImage(infoBean);
		this.status = QutiaoConstant.PRODUCT_STAUS_VERIFY;
		this.description = QutiaoProductParser
				.convertInfoBeanToProductDescription(infoBean);
	}

	/** minimal constructor */
	public QutiaoProduct(Timestamp createTime, Timestamp modifyTime) {
		this.createTime = createTime;
		this.modifyTime = modifyTime;
	}

	/** full constructor */
	public QutiaoProduct(Timestamp createTime, Timestamp modifyTime,
			String url, Long agree, Long disagree, String content,
			String title, String image, Integer status, String description) {
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.url = url;
		this.agree = agree;
		this.disagree = disagree;
		this.content = content;
		this.title = title;
		this.image = image;
		this.status = status;
		this.description = description;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

}