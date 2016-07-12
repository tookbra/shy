package com.tookbra.shy.domain;

import java.io.Serializable;

/**
 * Created by tookbra on 2016/6/23.
 */
public class Page implements Serializable {

    private int page = 1;
    private int pageSize = 10;

    public Page() {
    }

    public Page(int page, int pageSize) {
        this.pageSize = pageSize;
        this.page = page;
    }

    public Integer getPage() {
        return Integer.valueOf(this.page);
    }

    public void setPage(Integer page) {
        if(page.intValue() < 1) {
            page = Integer.valueOf(1);
        }

        this.page = page.intValue();
    }

    public Integer getPageSize() {
        return Integer.valueOf(this.pageSize);
    }

    public void setPageSize(Integer pageSize) {
        if(pageSize.intValue() < 1) {
            pageSize = Integer.valueOf(20);
        }

        this.pageSize = pageSize.intValue();
    }

    public int getStart() {
        return (this.page - 1) * this.pageSize + 1;
    }

    public int getEnd() {
        return this.page * this.pageSize;
    }
}
