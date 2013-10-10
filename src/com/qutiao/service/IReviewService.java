package com.qutiao.service;

import com.qutiao.dao.common.Page;
import com.qutiao.domain.QutiaoReview;

public interface IReviewService {
	public Page searchProductReview(long pid,int pageSize, int pageNum);

	public boolean addReview(QutiaoReview qutiaoReview);

	public boolean agreeReview(long rid);

	public boolean disagreeReview(long rid);
	
}
