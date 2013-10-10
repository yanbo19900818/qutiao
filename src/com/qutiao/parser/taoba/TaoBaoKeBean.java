package com.qutiao.parser.taoba;

public class TaoBaoKeBean {
    private String url = "http://gw.api.taobao.com/router/rest"; 
    private String appkey = "21309826";
    private String secret = "2077408678e007a06d64b4494d1e5214";
    private String TTID ;
    private String SID ;
    private long pid = 33801075;
    
    public String getUrl() {
        return url;
    }
    public String getAppkey() {
        return appkey;
    }
    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }
    public String getSecret() {
        return secret;
    }
    public void setSecret(String secret) {
        this.secret = secret;
    }
    public String getTTID() {
        return TTID;
    }
    public void setTTID(String tTID) {
        TTID = tTID;
    }
    public String getSID() {
        return SID;
    }
    public void setSID(String sID) {
        SID = sID;
    }
    public long getPid() {
        return pid;
    }
    public void setPid(long pid) {
        this.pid = pid;
    }
}
