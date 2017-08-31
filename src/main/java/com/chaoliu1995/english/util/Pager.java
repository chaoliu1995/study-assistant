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
	private Integer endNum;		//
	
	public Pager(Integer currentPage, Integer pageSize, Integer recordTotal){
		this.pageTotal = (int)Math.ceil((double)recordTotal / pageSize);
		if(currentPage > pageTotal)
			this.currentPage = pageTotal;
		else if(currentPage < 1)
			this.currentPage = 1;
		else
			this.currentPage = currentPage;
		
		this.endNum = currentPage * pageSize;
		this.startNum = this.endNum - pageSize;
		
		if(currentPage == 1)
			this.prevPage = 1;
		else
			this.prevPage = currentPage - 1;
		if(currentPage == this.pageTotal)
			this.nextPage = currentPage;
		else
			this.nextPage = currentPage + 1;
	}
	
	public Pager(Integer currentPage, Integer recordTotal, Integer pageSize, List<T> recordList){
		this.currentPage = currentPage;
		this.recordTotal = recordTotal;
		this.pageSize = pageSize;
		this.recordList = recordList;
		this.pageTotal = (int)Math.ceil((double)recordTotal / pageSize);
		
		if(currentPage == 1)
			this.prevPage = 1;
		else
			this.prevPage = currentPage - 1;
		if(currentPage == this.pageTotal)
			this.nextPage = currentPage;
		else
			this.nextPage = currentPage + 1;
	}
	
}
