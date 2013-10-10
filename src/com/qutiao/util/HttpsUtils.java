package com.qutiao.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpsUtils {
	
    private static Log log = LogFactory.getLog("System");


    private static X509TrustManager tm = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] xcs, String string)
                            throws CertificateException {
            }
            public void checkServerTrusted(X509Certificate[] xcs, String string)
                            throws CertificateException {
            }
            public X509Certificate[] getAcceptedIssuers() {
                    return null;
            }
    };

    @SuppressWarnings("deprecation")
    private static HttpClient getInstance() throws KeyManagementException,
                    NoSuchAlgorithmException {
            HttpClient client = new DefaultHttpClient();
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[] { tm }, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = client.getConnectionManager();
            SchemeRegistry sr = ccm.getSchemeRegistry();
            sr.register(new Scheme("https", ssf, 443));
            return new DefaultHttpClient(ccm, client.getParams());
    }

    public static String httpsPost(String url) {
    	HttpClient httpsClient=null;
    	try {				
    		httpsClient=getInstance();
    		HttpPost httpPost=new HttpPost(url);
    		HttpResponse response = httpsClient.execute(httpPost);
    		HttpEntity entity = response.getEntity();                
    		BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
    		StringBuffer content = new StringBuffer();
    		for (String line; (line = br.readLine()) != null;) {
    			content.append(line + "\r\n");
    		}
    		return content.toString();
		} catch (Exception e) {
			log.error("HttpsUtil failed");
			return null;
		}	finally {
			if(httpsClient!=null)
			httpsClient.getConnectionManager().shutdown();
        }
    }
    
    
    public static void main(String[] args) throws KeyManagementException,
                    NoSuchAlgorithmException, IllegalStateException, IOException {
            HttpClient httpsClient = HttpsUtils.getInstance();
            HttpPost httpPost=new HttpPost("https://api.weibo.com/oauth2/access_token?client_secret=a423c48a46f48532d7652d65c0736c3d&grant_type=authorization_code&redirect_uri=http://www.qutiao.com&code=cf3d91c30d196165059aa8dc3cbd2689&client_id=3066877656&");
            HttpResponse response = httpsClient.execute(httpPost);
            HttpEntity entity = response.getEntity();                
            BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
            StringBuffer content = new StringBuffer();
            for (String line; (line = br.readLine()) != null;) {
                    content.append(line + "\r\n");
            }
            System.err.println(content.toString());
    }
}