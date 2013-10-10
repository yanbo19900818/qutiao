package com.qutiao.service;

import java.util.List;

import com.qutiao.dao.common.Page;
import com.qutiao.domain.QutiaoProduct;
import com.qutiao.vo.ProductDetail;

public interface IProductService {

	public Page searchProductByKeywordId(long kid, int pageNum, int pageSize);

	public Page searchByKeyword(String keyWord, int pageSize, int pageNum);

	public Page searchAll(int pageSize, int pageNum);

	public ProductDetail searchDetail(long pid, int reviewPage);

	public boolean agreeProduct(long pid);

	public boolean disagreeProduct(long pid);

	public boolean addProduct(QutiaoProduct qutiaoProduct);

	/**
	 * 判断topicid是否存在数据库中，true代表存在，false代表不存在
	 * 
	 * @param topicId
	 * @return
	 */
	boolean isProductExistByTopicId(long topicId);

	Page getVerifyInfoList(int page, int pageSize);

	QutiaoProduct getProductById(long id);

	boolean deleteInfoById(long id);

	boolean verifyDisplayById(long id, String[] keywords);

	List<QutiaoProduct> randomProduct(int num);
}
