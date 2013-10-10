package com.qutiao.vo;

import com.qutiao.dao.common.Page;
import com.qutiao.domain.QutiaoProduct;

public class ProductDetail extends ProductVO {
/**
	 * 
	 */
	private static final long serialVersionUID = 7813398041375034302L;
private  Page reviewPage;

public Page getReviewPage() {
	return reviewPage;
}

public void setReviewPage(Page reviewPage) {
	this.reviewPage = reviewPage;
}

}
