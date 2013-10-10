package com.qutiao.service;

import com.qutiao.domain.QutiaoUser;

public interface IUserService {

	public QutiaoUser seachQutiaoUserByEmail(String email);

	public QutiaoUser seachQutiaoUserByUsername(String username);

	public boolean registerUser(QutiaoUser qutiaoUser);

	public boolean updateLastLoginTime(long uid);

	/**
	 * 激活用户
	 * 
	 * @param uid
	 *            用户id
	 * @param activeCode
	 *            激活码
	 * @return constant中常量
	 */
	public int activeUser(long uid, String activeCode);

	public QutiaoUser searchById(long uid);

	public boolean modifyUser(QutiaoUser user);

}
