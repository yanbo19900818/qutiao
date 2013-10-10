package com.qutiao.dao;

import java.util.List;

import com.qutiao.dao.common.IBaseDao;
import com.qutiao.dao.common.Page;
import com.qutiao.domain.QutiaoReview;

public interface IReviewDao extends IBaseDao<QutiaoReview> {

	public Page searchReviewByPid(long pid,int pageSize,int pageNum);

	public List<QutiaoReview> searchReviewByTargetId(long targetId);
	
	public boolean disagree(long rid);
	public boolean agree(long rid);

}
