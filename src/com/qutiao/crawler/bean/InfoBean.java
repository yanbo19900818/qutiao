package com.qutiao.crawler.bean;

import java.util.List;

public class InfoBean {

    private List<InfoContentBean> content;
    private int board_id;
    private long create_time;
    private int favor_num;
    private String link;
    private int replies;
    private int share_num;
    private String source_name;
    private String title;
    private int download_num;
    private long topic_id;
    private String baseurl;
    private int is_favors;
    
    public List<InfoContentBean> getContent() {
        return content;
    }
    public void setContent(List<InfoContentBean> content) {
        this.content = content;
    }
    public int getBoard_id() {
        return board_id;
    }
    public void setBoard_id(int boardId) {
        board_id = boardId;
    }
    public long getCreate_time() {
        return create_time;
    }
    public void setCreate_time(long createTime) {
        create_time = createTime;
    }
    public int getFavor_num() {
        return favor_num;
    }
    public void setFavor_num(int favorNum) {
        favor_num = favorNum;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public int getReplies() {
        return replies;
    }
    public void setReplies(int replies) {
        this.replies = replies;
    }
    public int getShare_num() {
        return share_num;
    }
    public void setShare_num(int shareNum) {
        share_num = shareNum;
    }
    public String getSource_name() {
        return source_name;
    }
    public void setSource_name(String sourceName) {
        source_name = sourceName;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getDownload_num() {
        return download_num;
    }
    public void setDownload_num(int downloadNum) {
        download_num = downloadNum;
    }
    public long getTopic_id() {
        return topic_id;
    }
    public void setTopic_id(long topicId) {
        topic_id = topicId;
    }
    public String getBaseurl() {
        return baseurl;
    }
    public void setBaseurl(String baseurl) {
        this.baseurl = baseurl;
    }
    public int getIs_favors() {
        return is_favors;
    }
    public void setIs_favors(int isFavors) {
        is_favors = isFavors;
    }

}
