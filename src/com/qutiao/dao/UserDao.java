package com.qutiao.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qutiao.dao.common.BaseDao;
import com.qutiao.domain.QutiaoUser;

@Repository
public class UserDao extends BaseDao<QutiaoUser> implements IUserDao {

	@Override
	public QutiaoUser searchUserByEmail(String email) {
		// TODO Auto-generated method stub
		List<QutiaoUser> users = searchByHQL("from QutiaoUser where email='"
				+ email + "'");
		if (users == null || users.size() <= 0)
			return null;
		else
			return users.get(0);
	}

	@Override
	public boolean updateLastLoginTime(long uid) {
		// TODO Auto-generated method stub
		boolean flag = executeSQL("UPDATE qutiao_user SET last_login_time=CURRENT_TIMESTAMP WHERE id="
				+ uid);
		return flag;
	}

	@Override
	public boolean updateUser(QutiaoUser user) {
		boolean flag = modify(user);
		return flag;
	}

	@Override
	public boolean updateIsActived(long uid) {
		// TODO Auto-generated method stub
		boolean flag = executeSQL("UPDATE qutiao_user SET is_actived=1  WHERE id="
				+ uid);
		return flag;
	}



	@Override
	public QutiaoUser searchUserByUsername(String username) {
		List<QutiaoUser> users = searchByHQL("from QutiaoUser where email='"
				+ username + "'");
		if (users == null || users.size() <= 0)
			return null;
		else
			return users.get(0);
	}
}
