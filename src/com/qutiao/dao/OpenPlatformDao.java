package com.qutiao.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qutiao.dao.common.BaseDao;
import com.qutiao.domain.QutiaoOpenPlatform;
@Repository
public class OpenPlatformDao extends BaseDao<QutiaoOpenPlatform>  implements IOpenPlatformDao{

	@Override
	public QutiaoOpenPlatform getOPBeanByOpenId(String openId, int openType) {
		String hql="from QutiaoOpenPlatform where open_id='"+openId+"' and open_type="+openType;
		List<QutiaoOpenPlatform> opList=this.searchByHQL(hql);
		if (opList == null || opList.size() <= 0)
			return null;
		else
			return opList.get(0);
	}

}
