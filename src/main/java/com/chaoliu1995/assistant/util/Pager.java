package com.chaoliu1995.assistant.util;

import lombok.Data;

/**
 * @Author: ChaoLiu
 * @Description: 分页工具类
 * @Date: 2018/11/20 16:06
 */
@Data
public class Pager {

    /**
     * 默认每页的数据量
     */
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    /**
     * 默认当前页
     */
    private static final Integer DEFAULT_CURRENT_PAGE = 1;

    /**
     * 最大每页数量
     */
    private static final Integer MAX_PAGE_SIZE = 30;

    private Integer pageSize;	//每页的数据量

    private Integer currentPage;	//当前页

    private Integer total;	//数据总数

    private Integer pageTotal;	//总页数

    private Integer prevPage;	//上一页

    private Integer nextPage;	//下一页

    private Integer start;	//起始

    private Integer end;		//结束

    public Pager(Integer currentPage, Integer pageSize, Integer total){
        if(pageSize == null || pageSize <= 0){
            this.pageSize = DEFAULT_PAGE_SIZE;
        }else {
            this.pageSize = pageSize;
        }
        if(pageSize > MAX_PAGE_SIZE) {
            this.pageSize = MAX_PAGE_SIZE;
        }
        if(currentPage == null || currentPage <= 0){
            this.currentPage = DEFAULT_CURRENT_PAGE;
        }
        this.pageTotal = (int)Math.ceil((double)total / pageSize);	//计算总页数
        this.total = total;

        //防止超出已有页数
        if(currentPage > pageTotal)
            this.currentPage = pageTotal;
        else if(currentPage < 1)
            this.currentPage = 1;
        else
            this.currentPage = currentPage;

        this.end = this.currentPage * pageSize;	//计算分页结束值
        this.start = this.end - pageSize;		//计算分页起始值
        if(this.start < 0) {
            this.start = 0;
        }

        //计算上一页、下一页
        if(this.currentPage == 1) {
            this.prevPage = 1;
        }else {
            this.prevPage = this.currentPage - 1;
        }
        if(this.currentPage == this.pageTotal) {
            this.nextPage = this.currentPage;
        }else {
            this.nextPage = this.currentPage + 1;
        }
    }

}
