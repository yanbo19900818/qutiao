package com.qutiao.dao;

import com.qutiao.dao.common.IBaseDao;
import com.qutiao.domain.QutiaoKeyword;

public interface IKeywordDao extends IBaseDao<QutiaoKeyword> {
/**
 * 判断关键词是否存在
 * @param keyword 
 * @return true代表存在，false代表不存在
 */
	public boolean checkExsite(String keyword);
	
	public QutiaoKeyword searchByKeyword(String keyword);
}
