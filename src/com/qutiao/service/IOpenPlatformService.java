package com.qutiao.service;

import com.qutiao.domain.QutiaoOpenPlatform;

public interface IOpenPlatformService {
	
	QutiaoOpenPlatform getOPBeanByOpenId(String openId, int openType);	

	long saveOpBean(QutiaoOpenPlatform openPlatform);
}
