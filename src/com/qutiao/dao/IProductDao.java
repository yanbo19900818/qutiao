package com.qutiao.dao;

import java.util.List;

import com.qutiao.dao.common.IBaseDao;
import com.qutiao.dao.common.Page;
import com.qutiao.domain.QutiaoProduct;

public interface IProductDao extends IBaseDao<QutiaoProduct> {
	/**
	 * 根据时间查询出所有商品信息
	 */
	public Page searchAllProduct(int pageSize, int pageNum);

	/**
	 * 判断topicid是否存在数据库中，true代表存在，false代表不存在
	 * 
	 * @param topicId
	 * @return
	 */
	boolean isProductExistByTopicId(long topicId);

	/**
	 * 查询未审核商品列表信息
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Page getVerifyInfoList(int page, int pageSize);

	boolean deleteInfoById(long id);

	public boolean updateDisagreeNum(long id);

	public boolean updateAgreeNum(long id);
	/**
	 * 随即取若干个产品
	 * @param num
	 * @return
	 */
	public List<QutiaoProduct> randomProduct(int num);
}
