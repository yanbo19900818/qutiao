package com.qutiao.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qutiao.dao.common.BaseDao;
import com.qutiao.dao.common.Page;
import com.qutiao.domain.QutiaoReview;
@Repository
public class ReviewDao extends BaseDao<QutiaoReview> implements IReviewDao {

	@Override
	public Page searchReviewByPid(long pid, int pageSize, int pageNum) {
		String hql = "from QutiaoReview where pid=" + pid;
		Page page = searchByHQL(hql, pageSize, pageNum);
		return page;
	}

	@Override
	public List<QutiaoReview> searchReviewByTargetId(long targetId) {
		List<QutiaoReview> list = searchByHQL("from QutiaoReview where targetId="
				+ targetId);
		return list;
	}

	@Override
	public boolean disagree(long rid) {
		String sql = "update qutiao_review set disagree=disagree+1 where id=" + rid;
		return this.executeSQL(sql);
	}

	@Override
	public boolean agree(long rid) {
		String sql = "update qutiao_review set agree=agree+1 where id=" + rid;
		return this.executeSQL(sql);
	}

}
