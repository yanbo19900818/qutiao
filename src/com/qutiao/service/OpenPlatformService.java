package com.qutiao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qutiao.dao.IOpenPlatformDao;
import com.qutiao.domain.QutiaoOpenPlatform;
@Service
public class OpenPlatformService implements IOpenPlatformService{
	
	@Autowired
	IOpenPlatformDao opDao;

	@Override
	public QutiaoOpenPlatform getOPBeanByOpenId(String openId, int openType) {
		return opDao.getOPBeanByOpenId(openId, openType);
	}

	@Override
	public long saveOpBean(QutiaoOpenPlatform openPlatform) {
		return opDao.saves(openPlatform);
	}

}
