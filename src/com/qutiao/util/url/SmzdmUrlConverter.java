package com.qutiao.util.url;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

public class SmzdmUrlConverter {
	private static String getRedirectURL(String urlString) {
		URL url;
		try {
			url = new URL(urlString);

			URLConnection urlConnection = url.openConnection();
			HttpURLConnection httpUrlConnection = (HttpURLConnection) urlConnection;
			Parser parser = new Parser(httpUrlConnection);
			NodeList nodeList = parser
					.extractAllNodesThatMatch(new NodeFilter() {
						// 实现该方法,用以过滤标签
						public boolean accept(Node node) {
							if (node instanceof LinkTag)// <A>标记
								return true;
							return false;
						}
					});
			for (int i = 0; i < nodeList.size(); i++) {
				LinkTag n = (LinkTag) nodeList.elementAt(i);
				if ("直 达 链 接".equals(n.getStringText())) {
					return n.extractLink();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	private static String getRealUrl(String url) {

		try {
			HttpPost httpget = new HttpPost(url);

			httpget.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS,
					false);
			DefaultHttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(httpget);
			Header[] header = response.getHeaders("location");
			url = header[0].getValue();
			client.getConnectionManager().shutdown();
			httpget = new HttpPost(url);
			client = new DefaultHttpClient();
			response = client.execute(httpget);
			header = response.getHeaders("location");
			client.getConnectionManager().shutdown();
			return header[0].getValue();
			// if (header != null) {
			// String newuri = header.getValue();
			// if ((newuri == null) || (newuri.equals("")))
			// newuri = "/";
			// GetMethod redirect = new GetMethod(newuri);
			// client.executeMethod(redirect);
			// System.out.println("Redirect:"+
			// redirect.getStatusLine().toString());
			// redirect.releaseConnection();
			// } else
			// System.out.println("Invalid redirect");
			// }

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	public static String converterUrl(String url) {
		return getRealUrl(getRedirectURL(url));
	}

	public static void main(String[] args) {
		String url=converterUrl("http://www.smzdm.com/logitech-g9x-laser-gaming-mouse-5700dpi-can-be-redeployed-weight-439-yuan.html");
		System.out.println(url);
	}
}
