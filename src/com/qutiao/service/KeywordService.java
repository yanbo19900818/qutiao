package com.qutiao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qutiao.dao.IKeywordDao;
import com.qutiao.dao.IKeywordProductRelationDao;
import com.qutiao.dao.IProductDao;
import com.qutiao.dao.common.Page;
import com.qutiao.domain.QutiaoKeyword;
import com.qutiao.domain.QutiaoProduct;
import com.qutiao.domain.QutiaoProductKeywordRelation;
import com.qutiao.vo.KeywordVO;

@Service
public class KeywordService implements IKeywordService {
	@Autowired
	IKeywordDao keywordDao;
	@Autowired
	IKeywordProductRelationDao relationDao;
	@Autowired
	IProductDao productDao;

	@SuppressWarnings("unchecked")
	@Override
	public Page searchProductByKeywordId(long kid, int pageNum, int pageSize) {
		Page page = relationDao.searchKeywordRelationByKid(kid, pageSize,
				pageNum);
		return page;
	}

	@Override
	public QutiaoKeyword searchKeyword(String keyword) {
		// TODO Auto-generated method stub
		QutiaoKeyword qutiaoKeyword = keywordDao.searchByKeyword(keyword);
		return qutiaoKeyword;
	}

	@Override
	public long addKeyword(String keyWord) {
		// TODO Auto-generated method stub
		QutiaoKeyword qutiaoKeyword = new QutiaoKeyword();
		long id = keywordDao.saves(qutiaoKeyword);
		return id;
	}

	@Override
	public boolean deleteKeyword(long id) {
		// TODO Auto-generated method stub
		boolean flag = keywordDao.delete(id);
		return flag;
	}

	@Override
	public QutiaoKeyword searchKeywordById(long id) {
		// TODO Auto-generated method stub
		QutiaoKeyword qutiaoKeyword = keywordDao.searchById(id);
		return qutiaoKeyword;
	}

	@Override
	public Page searchProductByKeyWord(String keyword, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		QutiaoKeyword qutiaoKeyword = searchKeyword(keyword);
		if (qutiaoKeyword == null)
			return null;
		long id = qutiaoKeyword.getId();
		Page page = searchProductByKeywordId(id, pageNum, pageSize);
		return page;
	}

	@Override
	public List<KeywordVO> searchKeywordVOsByPid(long pid) {
		// TODO Auto-generated method stub
		List<QutiaoProductKeywordRelation> relations = relationDao
				.searchKeywordRelationByPid(pid);
		List<KeywordVO> keywordVOs = new ArrayList<KeywordVO>();
		for (QutiaoProductKeywordRelation relation : relations) {
			KeywordVO keywordVO = new KeywordVO();
			long kid = relation.getKid();
			if (kid <= 0)
				continue;
			QutiaoKeyword keyword = keywordDao.searchById(kid);
			keywordVO.setKid(kid);
			keywordVO.setPid(pid);
			keywordVO.setKeyWordName(keyword.getWord());
			keywordVOs.add(keywordVO);
		}
		return keywordVOs;
	}

	@Override
	public long addKeyword(QutiaoKeyword keyWord) {
		// TODO Auto-generated method stub
		long id = keywordDao.saves(keyWord);
		return id;
	}

}
