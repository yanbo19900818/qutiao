package com.qutiao.parser.taoba;

public class ParseBean {
    private int flag = 0;
    private int type = 0;//0 - ��Ʒ ,1 -����
    private String taobaoItemId;
    private String htmlContext;
    public String getHtmlContext() {
        return htmlContext;
    }
    public void setHtmlContext(String htmlContext) {
        this.htmlContext = htmlContext;
    }
    public int getFlag() {
        return flag;
    }
    public void setFlag(int flag) {
        this.flag = flag;
    }
    public String getTaobaoItemId() {
        return taobaoItemId;
    }
    public void setTaobaoItemId(String taobaoItemId) {
        this.taobaoItemId = taobaoItemId;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
}
