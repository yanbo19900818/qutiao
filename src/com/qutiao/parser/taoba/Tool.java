package com.qutiao.parser.taoba;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Tool {
    public static List list = new ArrayList();
    
    public URLBean getRedirectURL(String urlStr) throws Exception {
        if(list.isEmpty()){
            reloadMap();
        }
        URLBean bean = new URLBean();
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(15000);
        conn.setReadTimeout(15000);
        InputStream is = null;
        BufferedReader bin = null;
        try {
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");

            if (conn.getResponseCode() == 200) {
                String retString = conn.getURL().toString();
                if(isFile(retString)){
                    return null;
                }
                
                is = conn.getInputStream();
                bin = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String result = "";
                String s = null;
                while ((s = bin.readLine()) != null) {
                    result += s;
                }

                bean.setContent(result);
                bean.setRedirectURL(retString);

                return bean;
            }
            return null;
        } finally {
            if (conn != null)
                conn.disconnect();
            if (is != null) {
                is.close();
                is = null;
            }
            if (bin != null) {
                bin.close();
                bin = null;
            }

        }
    }

    /**
     * get ��������
     * @return
     * @throws Exception
     */
    public String getInvoke(String urlString) throws Exception {
        InputStream in = null;
        BufferedReader bin = null;
        try {
            URL url = new URL(urlString);
            in = url.openStream();
            bin = new BufferedReader(new InputStreamReader(in, "GB2312"));
            String s = null;
            String result = "";
            while ((s = bin.readLine()) != null) {
                result += s;
            }
            System.out.println(result);
            return result;
        } finally {
            if (bin != null)
                bin.close();
            if (in != null)
                in.close();
        }
    }

    public String getSinaShortUrl(String url) throws Exception {
        String tempUrl = "https://api.weibo.com/2/short_url/shorten.json?url_long=%1$s&source=1539309576";
        tempUrl = String.format(tempUrl, URLEncoder.encode(url, "UTF-8"));
        String responseStr = this.getInvoke(tempUrl);
        JSONObject object = JSONObject.fromObject(responseStr);
        JSONArray array = (JSONArray) object.get("urls");
        for (int i = 0; i < array.size(); i++) {
            JSONObject object2 = array.getJSONObject(i);
            if (object2.containsKey("url_short")) {
                return object2.getString("url_short");
            }
        }
        return responseStr;
    }
    
    public String getMobcentShortUrl(String url) throws Exception{
        String tempUrl = "http://mrl.so/um/urlMapping.do?longUrl=%1$s";
        tempUrl = String.format(tempUrl, URLEncoder.encode(url, "UTF-8"));
        String responseStr = this.getInvoke(tempUrl);
        JSONObject object = JSONObject.fromObject(responseStr);
        return "http://mrl.so/um/"+object.getString("shortCode");
    }

    /**
     * �����ҳ��title
     * @param s
     * @return
     */
    public String getTitle(final String s) {
        String regex;
        String title = "";
        final List<String> list = new ArrayList<String>();
        regex = "<title>.*?</title>";
        final Pattern pa = Pattern.compile(regex, Pattern.CANON_EQ);
        final Matcher ma = pa.matcher(s);
        while (ma.find()) {
            list.add(ma.group());
        }
        for (int i = 0; i < list.size(); i++) {
            title = title + list.get(i);
        }
        return outTag(title);
    }

    public String outTag(final String s) {
        return s.replaceAll("<.*?>", "");
    }

    /**
     * �������ƥ��Ľ��
     * @param str
     * @param regex
     * @return
     */
    public List getRegex(String str, String regex) {
        if (str == null)
            return null;

        List list = new ArrayList();
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(str);
        while (mat.find()) {
            list.add(mat.group());
        }
        return list;
    }
    
    public String getBoardEncode(int boardId){
        if(boardId != 0){
            String temp = "MobcentTangliangMake" + boardId;
            return UUID.nameUUIDFromBytes(temp.getBytes()).toString();
        }
        return null;
    }
    
    private void reloadMap(){
        list.add("apk");
        list.add("jpg");
        list.add("png");
        list.add("ipa");
        list.add("jpeg");
        list.add("tif");
        list.add("doc");
        list.add("xls");
        list.add("exe");
        list.add("bat");
        list.add("wmv");
        list.add("mp3");
        list.add("mp4");
        list.add("avi");
        list.add("rar");
        list.add("zip");
        list.add("tar");
        list.add("gz");
        list.add("wmv");
        list.add("rmv");
        list.add("rmvb");
        list.add("torrent");
    }
    
    private boolean isFile(String orginalUrl) throws MalformedURLException{
        URL url = new URL(orginalUrl);
        String path = url.getPath();
        for (int i = 0; i < list.size(); i++) {
            if(path.endsWith((String)list.get(i))){
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) throws Exception {
        Tool tool = new Tool();
        System.out.println(tool.getInvoke("http://192.168.1.207/mobcent/install/connection.php?test=1"));
    }
}
