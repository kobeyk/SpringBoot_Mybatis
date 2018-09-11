package com.appleyk.filter;

import com.appleyk.dict.OrderByEnum;

/**
 * @Description 基础查询过滤器
 * @Author Appleyk
 * @Blob https://blog.csdn.net/appleyk
 * @Date Created on 上午 11:11 2018-8-31
 */
public abstract class AFilter {

    /**
     * 页码
     */
    protected Integer pageNum = 1;

    /**
     * 页面大小
     */
    protected Integer pageSize = 0;


    /**
     * 升序或降序
     */
    protected boolean descOrAsc = false;


    protected OrderByEnum orderType = OrderByEnum.DEFAULT;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isDescOrAsc() {
        return descOrAsc;
    }

    public void setDescOrAsc(boolean descOrAsc) {
        this.descOrAsc = descOrAsc;
    }

    public OrderByEnum getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderByEnum orderType) {
        this.orderType = orderType;
    }
}
