package com.qutiao.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qutiao.dao.common.BaseDao;
import com.qutiao.dao.common.Page;
import com.qutiao.domain.QutiaoProduct;
import com.qutiao.util.QutiaoConstant;

@Repository
public class ProductDao extends BaseDao<QutiaoProduct> implements IProductDao {

	@Override
	public Page searchAllProduct(int pageSize, int pageNum) {
		String hql = "from QutiaoProduct order by create_time desc";
		Page page = searchByHQL(hql, pageSize, pageNum);
		return page;
	}

	@Override
	public boolean isProductExistByTopicId(long topicId) {
		String hql = "from QutiaoProduct where topicId=" + topicId;
		List<QutiaoProduct> list = searchByHQL(hql);
		if (list == null || list.size() == 0)
			return false;
		else
			return true;
	}

	@Override
	public Page getVerifyInfoList(int page, int pageSize) {
		String hql = "from QutiaoProduct where status="
				+ QutiaoConstant.PRODUCT_STAUS_VERIFY;
		return searchByHQL(hql, pageSize, page);
	}

	@Override
	public boolean deleteInfoById(long id) {
		String hql = "delete from QutiaoProduct where id=" + id;
		return this.excuteHQL(hql);
	}

	public boolean updateAgreeNum(long id) {
		String sql = "update qutiao_product set agree=agree+1 where id=" + id;
		return this.executeSQL(sql);
	}

	public boolean updateDisagreeNum(long id) {
		String sql = "update qutiao_product set disagree=disagree+1 where id="
				+ id;
		return this.executeSQL(sql);
	}

	@Override
	public List<QutiaoProduct> randomProduct(int num) {
		// TODO Auto-generated method stub
		String sql = "select * FROM qutiao_product ORDER BY RAND() LIMIT " + num;
		List<QutiaoProduct> list = this.searchBySQL(sql);
		return list;
	}
}
