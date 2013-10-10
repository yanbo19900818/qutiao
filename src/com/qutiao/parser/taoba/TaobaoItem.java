package com.qutiao.parser.taoba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class TaobaoItem {
    public TaobaoLinkBean get(String getUrl,Integer msTimeout, String referer) throws Exception{
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager ();
        // ���ó�ʱʱ��
        connectionManager.getParams ().setConnectionTimeout(msTimeout);
        HttpClient httpClient = new HttpClient(connectionManager);
        InputStream in = null;
        BufferedReader bin = null;
        GetMethod postMethod = null;
        try {
            httpClient.setConnectionTimeout(15000);
            postMethod = new GetMethod(getUrl);
            httpClient.getParams().setAuthenticationPreemptive(true);
            List<Header> headers = new ArrayList<Header>();
            headers.add(new Header("Accept", "application/x-ms-application, image/jpeg, application/xaml+xml, image/gif, image/pjpeg, application/x-ms-xbap, */*"));
//            headers.add(new Header("Referer","http://s.click.taobao.com/t_js?tu=http%3A%2F%2Fs.click.taobao.com%2Ft%3Fe%3DzGU34CA7K%2BPkqB07S4%2FK0CFcRfH0GoT805sipKvJud0X%2FG4KVtp5trkwXp83uWRd%2BTAlOcdtpqKTg9EDhcVP9dJyaMzqbCUaZNmXqiLcQsIO6w%3D%3D%26ref%3D%26et%3DjFBB2swQjMkrIA%253D%253D"));
            headers.add(new Header("Referer", referer));
            
            headers.add(new Header("Accept-Language", "zh-CN"));
            headers.add(new Header( "User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Win64; x64; Trident/4.0; GTB7.2; .NET CLR 2.0.50727; SLCC2; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.3; .NET4.0C)"));
            headers.add(new Header ("Accept-Encoding","gzip, deflate"));
            headers.add(new Header("UA-CPU", "AMD64"));
            headers.add(new Header("Host" , "s.click.taobao.com"));
            headers.add(new Header("Connection", "Keep-Alive"));
            headers.add(new Header("Cookie" , "cna=7Z9BCQueM3ICAXEtjC6fhk8o; miid=849003384558395341; t=a65da5f1a447b4ba0ac398658e7d18e6; mt=ci=0_0; x=e%3D1%26p%3D*%26s%3D0%26c%3D0%26f%3D0%26g%3D0%26t%3D0"));
            
            httpClient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
            postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(1, false));
            postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, msTimeout);
            int status = httpClient.executeMethod(postMethod);
            TaobaoLinkBean bean = new TaobaoLinkBean();
            
            if (status == 200) {
                bean.setUrl(postMethod.getURI().toString());
                in = postMethod.getResponseBodyAsStream();
                GZIPInputStream gzin = new GZIPInputStream(in); 
                bin = new BufferedReader(new InputStreamReader(gzin, "GBK"));
                StringBuffer ret = new StringBuffer();
                String s = null;
                while((s=bin.readLine())!=null){
                    ret.append(s);
                }
                bean.setContent(ret.toString());
                return bean;
            } else {
                throw new Exception(String.format("Invoke remote server address %1$s error, return status = %2$s!", getUrl, status));
            }
        } catch (HttpException e) {
            throw new Exception(String.format("Invoke remote server address %1$s error!", getUrl), e);
        } catch (IOException e) {
            throw new Exception(String.format("Invoke remote server address %1$s error!", getUrl), e);
        } finally {
            if(postMethod != null)
                postMethod.releaseConnection();
            if(in != null){
                in.close();
                in = null;
            }
            if(bin != null){
                bin.close();
                bin = null;
            }
            
        }
    }
}
