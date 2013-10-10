package com.qutiao.dao;

import com.qutiao.dao.common.IBaseDao;
import com.qutiao.domain.QutiaoUser;

public interface IUserDao extends IBaseDao<QutiaoUser> {
	/**
	 * 判断关用户是否存在
	 * 
	 * @param username
	 * @return true代表存在，false代表不存在
	 */
	
	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	public QutiaoUser searchUserByUsername(String username);
	
	public QutiaoUser searchUserByEmail(String email);
	public boolean updateLastLoginTime(long uid);
    
	public boolean updateUser(QutiaoUser user);
	
	public boolean updateIsActived(long uid);
	
}
