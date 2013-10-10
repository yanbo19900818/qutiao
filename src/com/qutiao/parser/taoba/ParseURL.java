package com.qutiao.parser.taoba;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class ParseURL {
	public final static String TAOBAO_FLAG = "taobao.com";
	public final static TaoBaoKe tbk = new TaoBaoKe();
	private static TaobaoItem item = new TaobaoItem();
	private static Tool tool = new Tool();
	private final static String REGEX = "http://s.click.taobao.com/[\\w.//?=&%.]+";
	public int successCount = 0;
	public int failedCount = 0;

	/**
	 * ͨ��url���itemId
	 * 
	 * @param url
	 * @return
	 */
	public ParseBean parse(String url) {
		System.out.println("original url is:" + url);
		ParseBean pbBean = new ParseBean();
		try {
			pbBean.setFlag(0); // default is false ,
			URLBean ubean = tool.getRedirectURL(url);
			String rurl = ubean.getRedirectURL();
			String content = ubean.getContent();
			System.out.println("Frist return url is:" + rurl);
			// System.out.println("Frist return content is:" + content);
			if (rurl == null) {
				return pbBean;
			} else {
				URL ururl = new URL(rurl);
				if (!ururl.getHost().contains("taobao.com")
						&& !ururl.getHost().contains("tmall.com")) {
					if (content.length() > 700) { // ����ַ���700���ϣ��Ͳ��÷����ˣ�����̫��
						return pbBean;
					}

					List list = tool.getRegex(content, REGEX);
					if (list == null || list.size() > 0) {
						String taobaoclickurl = (String) list.get(0);
						return parse(taobaoclickurl);
					} else {
						return pbBean;
					}
				}
			}

			URL uurl = new URL(rurl);
			String host = uurl.getHost();
			String query = uurl.getQuery();
			query = URLDecoder.decode(query, "UTF-8");
			Map paramMap = this.getParamMap(query);
			if (host.equals("s.click.taobao.com")) {
				// �õ�paramMap

				if (!paramMap.containsKey("tu")) { // û���ҵ���־
					return pbBean;
				}

				// get��url��Ҫ����
				String getUrl = getURL(paramMap);
				TaobaoLinkBean tBean = item.get(getUrl, 30000, rurl);
				System.out.println("Return url is:" + tBean.getUrl());
				pbBean.setFlag(1);
				this.getItemId(tBean, pbBean);
				return pbBean;
			} else if (host.equals("item.taobao.com")) {
				if (!paramMap.containsKey("id")) { // û���ҵ���־
					return pbBean;
				}

				pbBean.setFlag(1);
				pbBean.setTaobaoItemId(paramMap.get("id").toString());
				return pbBean;
			}

			if (host.contains("taobao.com")) {
				pbBean.setFlag(1);
				TaobaoLinkBean tBean = new TaobaoLinkBean();
				tBean.setUrl(rurl);
				tBean.setContent(content);
				this.getItemId(tBean, pbBean);
				return pbBean;
			}

			return pbBean;
		} catch (Exception e) {
			pbBean.setFlag(-1);
			return pbBean;
		}
	}

	/**
	 * ��װ���õ�url , referΪ�ʼ��url
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	private String getURL(Map paramMap) throws Exception {
		String tuUrl = paramMap.get("tu").toString();
		String str = "";
		if (tuUrl.contains("%2")) { // part of url is urlencode , the other is
									// not.
			str = tuUrl;
		} else {
			URL utuUrl = new URL(tuUrl);
			String query = utuUrl.getQuery();
			int position = query.indexOf("=");
			String value = "";
			String key = "";
			if (position >= 0) {
				key = query.substring(0, position + 1);
				value = query.substring(position + 1, query.length());
			}
			value = URLEncoder.encode(value, "UTF-8");

			str = utuUrl.getProtocol() + "://" + utuUrl.getHost()
					+ utuUrl.getPath() + "?" + key + value;
		}

		Set entrySet = paramMap.entrySet();
		for (Iterator iterator = entrySet.iterator(); iterator.hasNext();) {
			Entry entry = (Entry) iterator.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			if (key.equals("tu"))
				continue;
			else {
				str += "&" + key + "=" + value;
			}
		}

		// str += "&ref=" + paramMap.get("ref").toString();
		// str += "&et=" + paramMap.get("et").toString();
		return str;
	}

	private void getItemId(TaobaoLinkBean tbean, ParseBean bean)
			throws MalformedURLException {
		URL uurl = new URL(tbean.getUrl());
		String file = uurl.getFile();
		String query = uurl.getQuery();
		Map paramMap = this.getParamMap(query);
		if (file.contains("/shop")) { // �Ա����̵�
			bean.setType(1);
			if (paramMap.get("user_number_id") != null)
				bean.setTaobaoItemId(paramMap.get("user_number_id").toString());
			if (paramMap.get("user_id") != null)
				bean.setTaobaoItemId(paramMap.get("user_id").toString());
			bean.setHtmlContext(tbean.getContent());
		} else {
			bean.setType(0);
			if (paramMap.get("id") != null)
				bean.setTaobaoItemId(paramMap.get("id").toString());
			if (paramMap.get("itemid") != null)
				bean.setTaobaoItemId(paramMap.get("itemid").toString());
		}
	}

	private Map getParamMap(String query) {
		String args[] = query.split("&");
		Map paramMap = new HashMap();
		for (int i = 0; i < args.length; i++) {
			int position = args[i].indexOf("=");
			if (position >= 0) {
				String key = args[i].substring(0, position);
				String value = args[i]
						.substring(position + 1, args[i].length());
				paramMap.put(key, value);
			}
		}
		return paramMap;
	}

	public String getTaobaokeLongUrlByOwner(String url, TaoBaoKeBean tbean,
			boolean isMobile) {
		System.out.println("===========begin to parse url=========");
		ParseBean bean = this.parse(url);
		if (bean.getFlag() == 1) {
			System.out.println("===========begin to get taobaoke url=========");
			String longUrl = tbk.getTaobaokeUrl(bean, tbean, isMobile);
			if (longUrl == null)
				failedCount++;
			else
				successCount++;
			System.out.println("taobaokeurl is :" + longUrl);
			return longUrl;
		}
		return null;
	}

	/**
	 * ��ü����Լ�pid��taobaoke����
	 * 
	 * @param url
	 *            ԭurl
	 * @param pid
	 *            �Լ���pid
	 * @return
	 */
	public String getTaobaokeURLByOwner(String url, TaoBaoKeBean tbean,
			boolean useMobcentShorUrl,boolean isMobile) {
		String longUrl = getTaobaokeLongUrlByOwner(url, tbean,isMobile);
		// try {
		// if (longUrl != null) {
		// String shortURL = null;
		// if (useMobcentShorUrl) {
		// System.out
		// .println("===========begin to get mobcent short url=========");
		// shortURL = tool.getMobcentShortUrl(longUrl);
		// System.out
		// .println("===========end to get mobcent short url=========");
		// } else {
		// System.out
		// .println("===========begin to get mobcent short url=========");
		// shortURL = tool.getSinaShortUrl(longUrl);
		// System.out
		// .println("===========end to get mobcent short url=========");
		// }
		// return shortURL;
		// }
		// return null;
		// } catch (Exception e) {
		// // e.printStackTrace();
		// return null;
		// }
		return longUrl;
	}

	public static void main(String[] args) throws Exception {
		ParseURL parseURL = new ParseURL();
		String url = parseURL
				.getTaobaokeURLByOwner(
						"http://s.click.taobao.com/t_9?p=mm_25282911_0_0&l=http%3a%2f%2fdetail.tmall.com%2fitem.htm%3fid%3d14875591823",
						new TaoBaoKeBean(), true,false);
		System.out.println(url);
	}
}
