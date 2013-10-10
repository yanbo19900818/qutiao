package com.qutiao.util.url.spread;

import com.qutiao.parser.taoba.ParseURL;
import com.qutiao.parser.taoba.TaoBaoKeBean;

public class TaobaokeUrlConverer implements IUrlConverter {
	public String convertWebUrl(String link) {
		// 判断淘宝客各种转换
		ParseURL parseURL = new ParseURL();
		String taobaokeLongUrl = parseURL.getTaobaokeLongUrlByOwner(link,
				new TaoBaoKeBean(), false);
		if (taobaokeLongUrl != null)
			return taobaokeLongUrl;
		return link;
	}

	public String convertPhoneUrl(String link) {
		// 判断淘宝客各种转换
		ParseURL parseURL = new ParseURL();
		String taobaokeLongUrl = parseURL.getTaobaokeLongUrlByOwner(link,
				new TaoBaoKeBean(), true);
		if (taobaokeLongUrl != null)
			return taobaokeLongUrl;
		return link;
	}


}
