package com.qutiao.dao;

import com.qutiao.dao.common.IBaseDao;
import com.qutiao.domain.QutiaoOpenPlatform;

public interface IOpenPlatformDao extends IBaseDao<QutiaoOpenPlatform> {
	
	 //根据openId、openType获取用户开放平台信息
	QutiaoOpenPlatform getOPBeanByOpenId(String openId, int openType);	
	
}
