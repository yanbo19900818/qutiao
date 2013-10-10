package com.qutiao.dao;

import java.util.List;

import com.qutiao.dao.common.IBaseDao;
import com.qutiao.dao.common.Page;
import com.qutiao.domain.QutiaoProductKeywordRelation;

public interface IKeywordProductRelationDao extends
		IBaseDao<QutiaoProductKeywordRelation> {
	
	public List<QutiaoProductKeywordRelation> searchKeywordRelationByPid(
			long pid);
	
	public Page searchKeywordRelationByKid(
			long kid,int pageSize,int pageNum);

}
