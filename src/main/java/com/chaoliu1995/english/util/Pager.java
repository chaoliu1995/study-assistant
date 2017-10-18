package com.chaoliu1995.english.util;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/** 
 * @ClassName: Pager<T> 
 * @Desc: TODO 分页
 * @author chao.Liu
 * @date 2017-06-26 19:00
 */
public class Pager<T> {
	
	public static final Integer DEFAULT_PAGE_SIZE = 10;		//默认每页的数据量
	
	public static final Integer DEFAULT_CURRENT_PAGE = 1;	//默认当前页
	
	@Getter
	private Integer pageSize;	//每页的数据量
	
	@Getter
	private Integer currentPage;	//当前页
	
	@Getter
	private Integer recordTotal;	//数据总数
	
	@Getter
	private Integer pageTotal;	//总页数
	
	@Getter
	@Setter
	private List<T> recordList;	//当前页数据列表
	
	@Getter
	private Integer prevPage;	//上一页
	
	@Getter
	private Integer nextPage;	//下一页
	
	@Getter
	private Integer startNum;	//起始
	
	@Getter
	private Integer endNum;		//结束
	
	public Pager(Integer currentPage, Integer pageSize, Integer recordTotal){
		if(pageSize == null || pageSize <= 0){
			pageSize = DEFAULT_PAGE_SIZE;
		}
		if(currentPage == null || currentPage <= 0){
			currentPage = DEFAULT_CURRENT_PAGE;
		}
		this.pageSize = pageSize;
		this.pageTotal = (int)Math.ceil((double)recordTotal / pageSize);	//计算总页数
		this.recordTotal = recordTotal;
		//防止超出已有页数
		if(currentPage > pageTotal)
			this.currentPage = pageTotal;
		else if(currentPage < 1)
			this.currentPage = 1;
		else
			this.currentPage = currentPage;
		
		this.endNum = this.currentPage * pageSize;	//计算分页结束值
		this.startNum = this.endNum - pageSize;		//计算分页起始值
		if(this.startNum < 0){
			this.startNum = 0;
		}
		
		//计算上一页、下一页
		if(this.currentPage == 1)
			this.prevPage = 1;
		else
			this.prevPage = this.currentPage - 1;
		
		if(this.currentPage == this.pageTotal)
			this.nextPage = this.currentPage;
		else
			this.nextPage = this.currentPage + 1;
	}
	
}
