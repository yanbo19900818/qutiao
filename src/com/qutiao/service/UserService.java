package com.qutiao.service;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qutiao.dao.IUserDao;
import com.qutiao.domain.QutiaoUser;
import com.qutiao.util.Constant;
import com.qutiao.util.MD5;
import com.qutiao.util.RegisterUtil;
import com.qutiao.util.mail.SendRegisterThread;

@Service
public class UserService implements IUserService {
	@Autowired
	IUserDao userDao;

	@Override
	public boolean registerUser(QutiaoUser qutiaoUser) {
		try {
			if (qutiaoUser.getPassword() != null)
				qutiaoUser.setPassword(MD5.md5(qutiaoUser.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		qutiaoUser.setActiveCode(RegisterUtil.getInstence().generateActiveCode(
				10));
		qutiaoUser.setCreateTime(new Timestamp(new Date().getTime()));
		qutiaoUser.setLastLoginTime(new Timestamp(new Date().getTime()));
		long uid = userDao.saves(qutiaoUser);
		if (uid <= 0)
			return false;
		else {
			String link = "http://www.qutiao.com/activeUser.do" + "?uid=" + uid + "&activeCode="
					+ qutiaoUser.getActiveCode();
			String emailString=qutiaoUser.getEmail();
			SendRegisterThread mailThread = new SendRegisterThread(
					"趣挑网注册确认邮件", qutiaoUser.getName(), link, qutiaoUser.getEmail());
			mailThread.setDaemon(true);
			mailThread.start();
			return true;
		}
	}

	@Override
	public boolean updateLastLoginTime(long uid) {
		// TODO Auto-generated method stub
		boolean flag = userDao.updateLastLoginTime(uid);
		return flag;
	}

	@Override
	public QutiaoUser seachQutiaoUserByEmail(String email) {
		// TODO Auto-generated method stub
		QutiaoUser user = userDao.searchUserByUsername(email);
		return user;
	}

	@Override
	public int activeUser(long uid, String activeCode) {
		// TODO Auto-generated method stub
		QutiaoUser user = userDao.searchById(uid);
		if (user == null)
			return Constant.NO_USER;
		if (!user.getActiveCode().equals(activeCode))
			return Constant.ACTIVE_CODE_WRONG;
		boolean flag = userDao.updateIsActived(uid);
		if (flag)
			return Constant.SUCCESS;
		else
			return Constant.UNKOWN;
	}

	@Override
	public QutiaoUser searchById(long uid) {
		QutiaoUser user = userDao.searchById(uid);
		return user;
	}

	@Override
	public boolean modifyUser(QutiaoUser user) {
		// TODO Auto-generated method stub
		boolean flag = userDao.modify(user);
		return flag;
	}

	@Override
	public QutiaoUser seachQutiaoUserByUsername(String username) {
		// TODO Auto-generated method stub
		QutiaoUser user = userDao.searchUserByEmail(username);
		return user;
	}

}
