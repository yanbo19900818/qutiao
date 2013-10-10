package com.qutiao.openPlatform;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.qutiao.openPlatform.bean.QQUserBean;
import com.qutiao.util.HttpUtils;
import com.qutiao.util.HttpsUtils;

public class OPLoginUtil {
	
	public static QQUserBean getQQOpenIdByToken(String accessToken){
		String url="https://graph.qq.com/oauth2.0/me";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("access_token", accessToken);
		String result=HttpUtils.httpPost(url, map);
		if(StringUtils.isNotEmpty(result)){
			Pattern pattern = Pattern.compile("\\{\"client_id\".*\\}");
			Matcher m = pattern.matcher(result);
			if(m.find()) result=m.group();
		}
		JSONObject jo=JSONObject.fromObject(result);
		QQUserBean qqUserBean=new QQUserBean();
		qqUserBean.setAccessToken(accessToken);
		qqUserBean.setClientId(jo.optString("client_id"));
		qqUserBean.setOpenId(jo.optString("openid"));
		return qqUserBean;
	}
	
	public static QQUserBean getSinaAccessTokenByCode(String code){
		String url="https://api.weibo.com/oauth2/access_token?client_secret=a423c48a46f48532d7652d65c0736c3d&grant_type=authorization_code&redirect_uri=http://www.qutiao.com&client_id=3066877656&code="+code;
		String result=HttpsUtils.httpsPost(url);
		if(StringUtils.isEmpty(result)) return null;
		JSONObject jo=JSONObject.fromObject(result);
		QQUserBean qqUserBean=new QQUserBean();
		qqUserBean.setAccessToken(jo.optString("access_token"));
		qqUserBean.setOpenId(jo.optString("uid"));
		return qqUserBean;
	}
	
}
