package com.qutiao.parser.taoba;

import java.util.List;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.TaobaokeItemDetail;
import com.taobao.api.domain.TaobaokeShop;
import com.taobao.api.request.TaobaokeItemsDetailGetRequest;
import com.taobao.api.request.TaobaokeShopsGetRequest;
import com.taobao.api.response.TaobaokeItemsDetailGetResponse;
import com.taobao.api.response.TaobaokeShopsGetResponse;

public class TaoBaoKe {
    private static Tool tool = new Tool();
    
    public String getTaobaokeUrl(ParseBean bean, TaoBaoKeBean tbean,boolean isMobile) {
        if(tbean == null){
            tbean = new TaoBaoKeBean();
        }
        
        if(bean.getType() == 0){ //item
            return getTaobaoItemUrl(bean, tbean,isMobile);
        } else { //shop
            return getTaobaoShopUrl(bean, tbean);
        }
      
    }
    
    /**
     * ���item������
     * @param pid
     * @param bean
     * @return
     */
    private String getTaobaoItemUrl(ParseBean bean, TaoBaoKeBean tbean,boolean isMoble){
        try {
            TaobaoClient client = new DefaultTaobaoClient(tbean.getUrl(), tbean.getAppkey(), tbean.getSecret());
            TaobaokeItemsDetailGetRequest req = new TaobaokeItemsDetailGetRequest();
            req.setFields("click_url,shop_click_url,seller_credit_score,num_iid,title,nick");
            req.setPid(tbean.getPid());
            req.setNumIids(bean.getTaobaoItemId());
            req.setIsMobile(isMoble);
            req.setOuterCode("picInfo");
            TaobaokeItemsDetailGetResponse response = client.execute(req);
            List taoBaoItemsList = response.getTaobaokeItemDetails();
            if(taoBaoItemsList != null){
                TaobaokeItemDetail detail = (TaobaokeItemDetail) taoBaoItemsList.get(0);
                String shortUrl = detail.getClickUrl();
                shortUrl = getTTIDUrl(shortUrl , tbean);
                return shortUrl;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private String getTTIDUrl(String longUrl, TaoBaoKeBean tbean) {
        if(tbean.getTTID() == null || tbean.getTTID().equals("")){
            return longUrl;
        }
        if(tbean.getSID() == null || tbean.getSID().equals("")){
            return longUrl;
        }
        longUrl += "&ttid="+tbean.getTTID()+"&sid="+tbean.getSID();
        return longUrl;
    }
    
    private String getTaobaoShopUrl(ParseBean bean, TaoBaoKeBean tbean){
        String title = tool.getTitle(bean.getHtmlContext());
        if(title != null){
            title = this.getTaobaoTitle(title);
            title = title.trim();
            System.out.println("shop title is:" + title);
        }
        
        try {
            TaobaoClient client = new DefaultTaobaoClient(tbean.getUrl(), tbean.getAppkey(), tbean.getSecret());
            TaobaokeShopsGetRequest req=new TaobaokeShopsGetRequest();
            req.setFields("click_url,shop_title");
            req.setKeyword(title);
            req.setPid(tbean.getPid());
            req.setIsMobile(true);
            req.setOuterCode("picInfo");
            TaobaokeShopsGetResponse response = client.execute(req);
            List list = response.getTaobaokeShops();
            if(list.size() > 0){
                TaobaokeShop shop = (TaobaokeShop)list.get(0);
                String shortUrl = shop.getClickUrl();
                shortUrl = getTTIDUrl(shortUrl , tbean);
                return shortUrl;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static void main(String[] args) {
        TaoBaoKe tbk = new TaoBaoKe();
        ParseBean bean = new ParseBean();
        bean.setFlag(0);
        bean.setTaobaoItemId("16863663778");
        System.out.println(tbk.getTaobaoShopUrl(bean, null));
    }
    
    private String getTaobaoTitle(String title){
        title = title.trim();
        title = title.replaceAll("��ҳ", "");
        title = title.replaceAll("�Ա���", "");
        title = title.replaceAll("�ֻ��Ա���", "");
        title = title.replaceAll("-", "");
        title = title.replaceAll("��èTmall.com", "");
        title = title.replaceAll("�B", " ");
        title = title.replaceAll("��" , " ");
        title = title.replaceAll("��" , " ");
        return title;
    }

}
