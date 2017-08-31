package com.chaoliu1995.english.service;

import com.chaoliu1995.english.base.BaseService;
import com.chaoliu1995.english.entity.shanbay.TabWord;
import com.chaoliu1995.english.model.ShanBayResult;
import com.chaoliu1995.english.util.Pager;

public interface TabWordService extends BaseService<TabWord> {
	
	/**
	 * 保存单词信息
	 * @param sbr
	 */
	void saveWord(ShanBayResult sbr,String savePath);
	
	/**
	 * 分页获取单词信息
	 * @return
	 */
	Pager<TabWord> listTabWordForExcel(Integer currentPage,Integer pageSize,Integer recordTotal);
	
}
