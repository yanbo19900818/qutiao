package com.qutiao.service;

import java.util.List;

import com.qutiao.dao.common.Page;
import com.qutiao.domain.QutiaoKeyword;
import com.qutiao.vo.KeywordVO;

public interface IKeywordService {

	public Page searchProductByKeywordId(long kid, int pageNum, int pageSize);

	public QutiaoKeyword searchKeyword(String keyword);

	public long addKeyword(String keyWord);

	public long addKeyword(QutiaoKeyword keyWord);

	public boolean deleteKeyword(long id);

	public QutiaoKeyword searchKeywordById(long id);

	public Page searchProductByKeyWord(String keyword, int pageNum, int pageSize);

	public List<KeywordVO> searchKeywordVOsByPid(long pid);
}
