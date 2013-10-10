package com.qutiao.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public final class HttpUtils {
    private static Log log = LogFactory.getLog("System");

    public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("client_id", "3066877656");
		map.put("client_secret", "a423c48a46f48532d7652d65c0736c3d");
		map.put("grant_type", "authorization_code");
		map.put("code", "36a2614246fdfbe05ae847222ad73d0f");
		map.put("redirect_uri", "http://www.qutiao.com");
        String result = httpPost("https://api.weibo.com/oauth2/access_token", map);
        System.out.println(result);
    }
    
    private static String appendParamToUrl(String url, Map<String, Object> map) {
        if (null == map || map.isEmpty()) {
            return url;
        }

        Set<String> keys = map.keySet();

        if (keys.isEmpty()) {
            return url;
        }

        Iterator<String> iterator = keys.iterator();
        for (int i = 0; iterator.hasNext(); i++) {
            String key = iterator.next();

            if (i == 0) {
                url += ("?" + key + "=" + map.get(key));
            } else {
                url += ("&" + key + "=" + map.get(key));
            }
        }

        return url;
    }

    private static List<NameValuePair> convertMap(Map<String, Object> map) {
        List<NameValuePair> result = new ArrayList<NameValuePair>();

        if (null == map || map.isEmpty()) {
            return result;
        }

        Set<String> keys = map.keySet();

        if (keys.isEmpty()) {
            return result;
        }

        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();

            result.add(new BasicNameValuePair(key, String.valueOf(map.get(key))));
        }

        return result;
    }

    private static String baseHttpGet(String url, Map<String, Object> map, DefaultHttpClient client) {
        String result = null;

        url = appendParamToUrl(url, map);
        HttpGet httpget = new HttpGet(url);
        System.out.println(url);
        if (null == client) {
            client = new DefaultHttpClient();
        }

        try {

            httpget.addHeader("Content-type", "text/html; charset=utf-8");
            httpget.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, new Integer(40000));

            HttpResponse response = client.execute(httpget);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                System.out.println("HttpGet method failed: " + response.getStatusLine());
            }

            result = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            log.error("httpclient failed");
        } finally {
            client.getConnectionManager().shutdown();
        }

        return result;
    }

    private static String baseHttpPost(String url, Map<String, Object> map, DefaultHttpClient client) {
        String result = null;

        HttpPost httpost = new HttpPost(url);

        if (null == client) {
            client = new DefaultHttpClient();
        }

        try {
            List<NameValuePair> nvps = convertMap(map);

            //添加header后参数发送不过去
            //			httpost.addHeader("Content-type", "text/html; charset=utf-8");
            httpost.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, new Integer(40000));
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            String string = url + "?";
            System.out.print(url + "?");
            for (String key : map.keySet()) {
                string = string + key + "=" + map.get(key) + "&";
                System.out.print(key + "=" + map.get(key) + "&");
            }
            System.out.println();
            log.error(string);
            HttpResponse response = client.execute(httpost);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                System.out.println("HttpGet method failed: " + response.getStatusLine());
            }

            result = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            log.error("httpclient Failed");
        } finally {
            client.getConnectionManager().shutdown();
        }

        return result;
    }

    public static String httpPost(String url, Map<String, Object> map) {
        return baseHttpPost(url, map, null);
    }

    public static String httpGet(String url, Map<String, Object> map) {
        return baseHttpGet(url, map, null);
    }

    public static String httpGzipGet(String url, Map<String, Object> map) {
        DefaultHttpClient client = new DefaultHttpClient();
        wrapGzipClient(client);
        return baseHttpGet(url, map, client);
    }

    public static String httpGzipPost(String url, Map<String, Object> map) {
        DefaultHttpClient client = new DefaultHttpClient();
        wrapGzipClient(client);
        return baseHttpPost(url, map, client);
    }

    private static void wrapGzipClient(DefaultHttpClient client) {
        client.addRequestInterceptor(new HttpRequestInterceptor() {

            public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
                if (!request.containsHeader("Accept-Encoding")) {
                    request.addHeader("Accept-Encoding", "gzip");
                }
            }
        });

        client.addResponseInterceptor(new HttpResponseInterceptor() {

            public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
                HttpEntity entity = response.getEntity();
                Header ceheader = entity.getContentEncoding();
                if (ceheader != null) {
                    HeaderElement[] codecs = ceheader.getElements();
                    for (int i = 0; i < codecs.length; i++) {
                        if (codecs[i].getName().equalsIgnoreCase("gzip")) {
                            response.setEntity(new GzipDecompressingEntity(response.getEntity()));
                        }
                    }
                }
            }
        });
    }
    
}
