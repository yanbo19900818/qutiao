package com.qutiao.util.url.spread;

public class UrlConvertFactory {
	public static IUrlConverter createUrlConverteg(String link) {
		if (link.startsWith("http://detail.tmall.com"))
			return new TaobaokeUrlConverer();
		if(link.startsWith("http://s.click.taobao.com"))
			return new TaobaokeUrlConverer();
		return null;
	}
}
