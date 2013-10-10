package com.qutiao.util.url;

public class UrlConvertTool {

	public final static String SMZDM_URL = "http://www.smzdm.com";
	public final static String ZHIZHIZHI_URL = "http://www.zhizhizhi.com";

	public static String getOriginalUrl(String link) {
		String url = link;
		if (link.startsWith(SMZDM_URL)) {
			url = SmzdmUrlConverter.converterUrl(link);
		} else if (link.startsWith(ZHIZHIZHI_URL)) {
			url = ZhizhizhiUrlConverter.convertUrl(link);
		}
		return url;
	}

}
