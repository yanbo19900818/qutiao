package com.qutiao.util.url.spread;

public class SpreedUrlConverterTool  {

	public static String convertWebUrl(String link) {
		IUrlConverter urlConverter = UrlConvertFactory.createUrlConverteg(link);
		if (urlConverter == null)
			return link;
		return urlConverter.convertWebUrl(link);
	}

	public static String convertPhoneUrl(String link) {
		IUrlConverter urlConverter = UrlConvertFactory.createUrlConverteg(link);
		if (urlConverter == null)
			return link;
		return urlConverter.convertPhoneUrl(link);
	}

}
