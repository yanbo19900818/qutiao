package com.qutiao.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qutiao.dao.common.BaseDao;
import com.qutiao.dao.common.Page;
import com.qutiao.domain.QutiaoProductKeywordRelation;
@Repository
public class KeywordProductRelationDao extends
		BaseDao<QutiaoProductKeywordRelation> implements
		IKeywordProductRelationDao {

	@Override
	public List<QutiaoProductKeywordRelation> searchKeywordRelationByPid(
			long pid) {
		// TODO Auto-generated method stub
		List<QutiaoProductKeywordRelation> list = searchByHQL("from QutiaoProductKeywordRelation where pid="
				+ pid);
		return list;
	}

	@Override
	public Page searchKeywordRelationByKid(long kid, int pageSize, int pageNum) {
		String hql = "from QutiaoProductKeywordRelation where kid=" + kid;
		Page page = searchByHQL(hql, pageSize, pageNum);
		return page;
	}
}
