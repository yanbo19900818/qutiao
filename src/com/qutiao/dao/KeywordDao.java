package com.qutiao.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qutiao.dao.common.BaseDao;
import com.qutiao.domain.QutiaoKeyword;
@Repository
public class KeywordDao extends BaseDao<QutiaoKeyword> implements IKeywordDao {

	@Override
	public boolean checkExsite(String keyword) {
		QutiaoKeyword qutiaoKeyword = searchByKeyword(keyword);
		if (qutiaoKeyword == null)
			return false;
		else
			return true;
	}

	@Override
	public QutiaoKeyword searchByKeyword(String keyword) {
		List<QutiaoKeyword> list = searchByHQL("from QutiaoKeyword where word='"
				+ keyword+"'");
		if (list == null || list.size() <= 0)
			return null;
		return list.get(0);
	}

}
