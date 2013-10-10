package com.qutiao.util;

import com.qutiao.crawler.bean.InfoBean;
import com.qutiao.crawler.bean.InfoContentBean;

public class QutiaoProductParser {
    
    public static String convertInfoBeanToProductContent(InfoBean infoBean){
        String content="";
        String baseUrl=infoBean.getBaseurl();
        for (InfoContentBean infoContentBean : infoBean.getContent()) {
            if("0".equals(infoContentBean.getType())){
                content+="<p>"+infoContentBean.getInfor()+"</p>";
            }else if ("1".equals(infoContentBean.getType())) {
                content+="<img src='"+baseUrl+infoContentBean.getInfor()+"'/>";
            }
        }
        return content;
    }
    
    public static String convertInfoBeanToProductDescription(InfoBean infoBean){
        String content="";
        for (InfoContentBean infoContentBean : infoBean.getContent()) {
            if("0".equals(infoContentBean.getType())){
                content+=infoContentBean.getInfor();
            }
        }
        if(content.length()>200){            
            return content.substring(0, 200)+"......";
        }else {
            return content;
        }
    }
    
    public static String convertInfoBeanToProductImage(InfoBean infoBean){
        String image="";
        String baseUrl=infoBean.getBaseurl();
        for (InfoContentBean infoContentBean : infoBean.getContent()) {
            if ("1".equals(infoContentBean.getType())) {
                image=baseUrl+infoContentBean.getInfor();
                break;
            }
        }
        return image;
    }

}
