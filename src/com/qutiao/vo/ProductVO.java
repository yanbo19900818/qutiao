package com.qutiao.vo;

import java.util.List;

import com.qutiao.domain.QutiaoProduct;
import com.qutiao.domain.QutiaoProductKeywordRelation;

public class ProductVO extends QutiaoProduct {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3387907375526207569L;
	private List<KeywordVO> keywords;

	public List<KeywordVO> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<KeywordVO> keywords) {
		this.keywords = keywords;
	}

}
