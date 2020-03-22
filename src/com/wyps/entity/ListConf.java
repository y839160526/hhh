package com.wyps.entity;

public class ListConf {

    private String url;
    private Path listPath = new Path();
    private Path nextUrl = new Path();
    private int pageCount;
    private boolean isNeedClick = false;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Path getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(Path nextUrl) {
        this.nextUrl = nextUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Path getListPath() {
        return listPath;
    }

    public void setListPath(Path listPath) {
        this.listPath = listPath;
    }

    public boolean isIsNeedClick() {
        return isNeedClick;
    }

    public void setIsNeedClick(boolean isNeedClick) {
        this.isNeedClick = isNeedClick;
    }

}
