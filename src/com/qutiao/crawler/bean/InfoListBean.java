package com.qutiao.crawler.bean;

public class InfoListBean {

    private int board_id;
    private int comment_num;
    private String contentAbbreviated;
    private long create_time;
    private int download_num;
    private int favors_num;
    private String image_url;
    private int share_num;
    private String source_name;
    private int source_type;
    private String title;
    private long topic_id;
    
    public int getBoard_id() {
        return board_id;
    }
    public void setBoard_id(int boardId) {
        board_id = boardId;
    }
    public int getComment_num() {
        return comment_num;
    }
    public void setComment_num(int commentNum) {
        comment_num = commentNum;
    }
    public String getContentAbbreviated() {
        return contentAbbreviated;
    }
    public void setContentAbbreviated(String contentAbbreviated) {
        this.contentAbbreviated = contentAbbreviated;
    }
    public long getCreate_time() {
        return create_time;
    }
    public void setCreate_time(long createTime) {
        create_time = createTime;
    }
    public int getDownload_num() {
        return download_num;
    }
    public void setDownload_num(int downloadNum) {
        download_num = downloadNum;
    }
    public int getFavors_num() {
        return favors_num;
    }
    public void setFavors_num(int favorsNum) {
        favors_num = favorsNum;
    }
    public String getImage_url() {
        return image_url;
    }
    public void setImage_url(String imageUrl) {
        image_url = imageUrl;
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
    public int getSource_type() {
        return source_type;
    }
    public void setSource_type(int sourceType) {
        source_type = sourceType;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public long getTopic_id() {
        return topic_id;
    }
    public void setTopic_id(long topicId) {
        topic_id = topicId;
    }

}
