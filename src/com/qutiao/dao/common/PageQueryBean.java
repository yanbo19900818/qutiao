package com.qutiao.dao.common;

import java.util.List;

public class PageQueryBean<T> {
    public boolean hasNextPage;
    public List<T> resultList;
    public int totalNum;

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }
}
