package com.qutiao.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qutiao.domain.QutiaoOpenPlatform;
import com.qutiao.domain.QutiaoUser;
import com.qutiao.openPlatform.OPLoginUtil;
import com.qutiao.openPlatform.bean.QQUserBean;
import com.qutiao.service.IOpenPlatformService;
import com.qutiao.service.IUserService;
import com.qutiao.util.Constant;
import com.qutiao.util.MD5;
import com.qutiao.util.mail.SendPasswordThread;
import com.qutiao.util.mail.SendRegisterThread;

@Controller
public class UserController {
	@Autowired
	IUserService userService;
	@Autowired
	IOpenPlatformService opService;

	@RequestMapping("/login.do")
	public String login(String email, String password, ModelMap map,
			HttpSession session) throws NoSuchAlgorithmException {
		if (email == null || password == null) {
			map.put("message", "请输入正确的用户名秘密");
			return "redirect:/jsp/login.jsp";
		}
		QutiaoUser qutiaoUser = userService.seachQutiaoUserByEmail(email);
		if (qutiaoUser == null) {
			map.put("message", "用户不存在");
			return "redirect:/jsp/login.jsp";
		}
		if (!qutiaoUser.getPassword().equals(MD5.md5(password))) {
			map.put("message", "用户名或密码错");
			return "redirect:/jsp/login.jsp";
		}
		if (!qutiaoUser.getIsActived()) {
			map.put("message", "用户未激活");
			return "redirect:/jsp/login.jsp";

		}
		userService.updateLastLoginTime(qutiaoUser.getId());
		session.setAttribute("user", qutiaoUser);
		return "redirect:index.html";
	}

	@RequestMapping("/loginByOp.html")
	public String loginByOp(@RequestParam("go") String go, ModelMap modelMap) {
		String url = "";
		if ("qq".equals(go)) {
			url = "https://graph.qq.com/oauth2.0/authorize?response_type=token&client_id=100359685&redirect_uri=http%3A%2F%2Fwww.qutiao.com%2Fjsp%2FloginByOp.jsp%3FopenType%3D1";
		} else if ("sina".equals(go)) {
			url = "https://api.weibo.com/oauth2/authorize?client_id=1094694748&response_type=code&redirect_uri=http%3A%2F%2Fwww.qutiao.com%2Fjsp%2FloginByOp.jsp%3FopenType%3D2";
		}
		return "redirect:" + url;
	}

	@RequestMapping("/loginByOp.do")
	public String loginByOp(@RequestParam("openType") Integer openType,
			@RequestParam("access_token") String accessToken, ModelMap map,
			HttpSession session) throws NoSuchAlgorithmException {
		QQUserBean qqUserBean = null;
		if (openType == null) {
			return "login";
		} else if (openType == 1) {
			qqUserBean = OPLoginUtil.getQQOpenIdByToken(accessToken);
		} else if (openType == 2) {
			qqUserBean = OPLoginUtil.getSinaAccessTokenByCode(accessToken);
		}
		if (qqUserBean != null) {
			QutiaoOpenPlatform opBean = opService.getOPBeanByOpenId(
					qqUserBean.getOpenId(), openType);
			// 根据openId判断是否注册过
			if (opBean != null && opBean.getUserId() != null) {
				QutiaoUser qutiaoUser = userService.searchById(opBean
						.getUserId());
				userService.updateLastLoginTime(qutiaoUser.getId());
				session.setAttribute("user", qutiaoUser);
				return "redirect:index.html";
			} else {
				if (openType == 2)
					accessToken = qqUserBean.getOpenId();
			}
		}
		map.put("accessToken", accessToken);
		map.put("openType", openType);
		return "registerByOp";
	}

	@RequestMapping("/registerByOp.do")
	public String registerByOp(@RequestParam("email") String email,
			@RequestParam("username") String username,
			@RequestParam("accessToken") String accessToken,
			@RequestParam("openType") Integer openType, ModelMap map,
			HttpSession session) throws NoSuchAlgorithmException {
		QutiaoUser user = new QutiaoUser();
		user.setEmail(email);
		user.setName(username);
		user.setIsActived(true);
		if (userService.registerUser(user)) {
			if (openType != null) {
				String openId = null;
				if (openType == 1) {
					openId = OPLoginUtil.getQQOpenIdByToken(accessToken)
							.getOpenId();
				} else if (openType == 2) {
					openId = accessToken;
				}
				if (openId != null) {
					QutiaoOpenPlatform opBean = opService.getOPBeanByOpenId(
							openId, openType);
					if (opBean == null) {
						opBean = new QutiaoOpenPlatform(user.getId(), openId,
								openType);
						System.out.println(opBean.getOpenId());
						if (opService.saveOpBean(opBean) > 0) {
							userService.updateLastLoginTime(user.getId());
							session.setAttribute("user", user);
							return "redirect:index.html";
						}
					}
				}
			}
		}
		map.put("accessToken", accessToken);
		return "registerByOp";
	}

	@RequestMapping("/register.do")
	public String register(String email, String password, String password2,
			String username, ModelMap map) {
		if (StringUtils.isBlank(email) || StringUtils.isBlank(password)
				|| StringUtils.isBlank(password2)
				|| StringUtils.isBlank(username)) {
			map.put("message", "必填项必须填写");
			return "redirect:/jsp/register.jsp";
		}
		if (!password.equals(password2)) {
			map.put("message", "两次密码输入不一致");
			return "redirect:/jsp/register.jsp";
		}
		QutiaoUser user = new QutiaoUser();
		user.setEmail(email);
		user.setPassword(password);
		user.setName(username);
		user.setIsActived(true);
		userService.registerUser(user);

		return "redirect:/jsp/register_success.jsp";
	}

	@RequestMapping("/logOut.do")
	public String logOut(HttpSession session) {
		session.invalidate();
		return "redirect:index.html";
	}

	@RequestMapping("/checkEmail.do")
	@ResponseBody
	public String checkEmail(String email) {
		if (StringUtils.isBlank(email))
			return "{rs:-1}";
		QutiaoUser user = userService.seachQutiaoUserByEmail(email);
		if (user == null)
			return "{rs:0}";
		else
			return "{rs:1}";
	}

	@RequestMapping("/checkUsername.do")
	@ResponseBody
	public String checkUsername(String username) {
		if (StringUtils.isBlank(username))
			return "{rs:-1}";
		QutiaoUser user = userService.seachQutiaoUserByUsername(username);
		if (user == null)
			return "{rs:0}";
		else
			return "{rs:1}";
	}

	@RequestMapping("/selfInfo.html")
	public String searchSelf(HttpSession session, ModelMap map) {
		QutiaoUser user = (QutiaoUser) session.getAttribute("user");
		map.put("self", user);
		return "user_center";
	}

	@RequestMapping("/updatSelfInfo.do")
	public String updateSelfInfo(HttpSession session, String name) {
		QutiaoUser user = (QutiaoUser) session.getAttribute("user");
		QutiaoUser self = userService.searchById(user.getId());
		self.setName(name);
		userService.modifyUser(user);
		return "redirect:selfInfo.html";
	}

	@RequestMapping("/updateSelfInfo.html")
	public String gotoUpdateSelfInfo(HttpSession session, ModelMap map) {
		QutiaoUser user = (QutiaoUser) session.getAttribute("user");
		QutiaoUser self = userService.searchById(user.getId());
		map.put("self", self);
		map.put("loginTime", user.getLastLoginTime());
		return "update_self_info";
	}

	@RequestMapping("/resetPassword.html")
	public String gotoresetPassword(HttpSession session, ModelMap map) {
		return "reset_password";
	}

	@RequestMapping("/resetPassword.do")
	public String resetPassword(HttpSession session, String newpassword,
			String newpassword2, String oldpassword, ModelMap map)
			throws NoSuchAlgorithmException {
		if (StringUtils.isBlank(oldpassword)
				|| StringUtils.isBlank(newpassword)
				|| StringUtils.isBlank(newpassword2)) {
			map.put("message", "请不要输入空白内容");
			return "/resetPassword.html";
		}

		if (!newpassword.equals(newpassword2)) {
			map.put("message", "两次输入的密码不一致");
			return "/resetPassword.html";
		}
		QutiaoUser user = (QutiaoUser) session.getAttribute("user");
		QutiaoUser self = userService.searchById(user.getId());
		if (!self.getPassword().equals(MD5.md5(oldpassword))) {
			map.put("message", "密码有误，请重新输入");
			return "/resetPassword.html";
		}
		self.setPassword(MD5.md5(newpassword));
		userService.modifyUser(user);
		return "redirect:selfInfo.html";
	}

	@RequestMapping("/activeUser.do")
	public String activeUser(long uid, String activeCode, ModelMap map) {
		if (StringUtils.isBlank(activeCode)) {
			map.put("message", "激活码错误");
			return "redirect:/jsp/user/active_user_failed.jsp";
		}
		int status = userService.activeUser(uid, activeCode);
		if (status == Constant.ACTIVE_CODE_WRONG) {
			map.put("message", "激活码错误");
			return "redirect:/jsp/user/active_user_failed.jsp";
		} else if (status == Constant.NO_USER) {
			map.put("message", "用户不存在");

			return "redirect:/jsp/user/active_user_failed.jsp";
		}
		return "redirect:/jsp/user/active_user_success.jsp";
	}

	@RequestMapping("/sendPasswordEmail.do")
	public String sendForgetPasswordEmail(String email, ModelMap map) {
		if (StringUtils.isBlank(email)) {
			map.put("message", "email格式错误");
			return "redirect:/jsp/user/forgetpassword/forget_password.jsp";
		}
		QutiaoUser user = userService.seachQutiaoUserByEmail(email);
		if (user == null) {
			map.put("message", "该用户不存在");
			return "redirect:/jsp/user/forgetpassword/forget_password.jsp";
		}

		String link = "http://www.qutiao.com/startResetPassword.do" + "?uid="
				+ user.getId() + "&secretKey=" + user.getActiveCode();
		SendPasswordThread mailThread = new SendPasswordThread("趣挑网重置密码确认邮件",
				user.getName(), user.getEmail(), link);
		mailThread.setDaemon(true);
		mailThread.start();
		return "redirect:/jsp/user/forgetpassword/send_email_success.jsp";
	}

	@RequestMapping("/startResetPassword.do")
	public String startResetPassword(long uid, String secretKey, ModelMap map) {
		map.put("uid", uid);
		map.put("secrectKey", secretKey);
		return "/user/forgetpassword/reset_password";
	}

	@RequestMapping("/forgetPassword.do")
	public String forgetPassword(long uid, String secrectKey, ModelMap map,
			String newPwd, String newPwd2) {
		if (StringUtils.isBlank(secrectKey) || StringUtils.isBlank(newPwd)
				|| StringUtils.isBlank(newPwd2)) {
			map.put("message", "参数不正确，请重新前往邮箱重置");
			return "redirect:/jsp/user/forgetpassword/send_email_failed.jsp";
		}
		if (!newPwd2.equals(newPwd)) {
			map.put("message", "两次输入的密码不正确");
			return "redirect:/jsp/user/forgetpassword/send_email_failed.jsp";
		}
		QutiaoUser user = userService.searchById(uid);
		if (user == null) {
			map.put("message", "该用户不存在");
			return "redirect:/jsp/user/forgetpassword/send_email_failed.jsp";
		}
		if (!user.getActiveCode().equals(secrectKey)) {
			map.put("message", "secrectKey不正确");
			return "redirect:/jsp/user/forgetpassword/send_email_failed.jsp";
		}
		try {
			user.setPassword(MD5.md5(newPwd));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userService.modifyUser(user);
		return "redirect:/jsp/user/forgetpassword/reset_password_success.jsp";
	}
}
