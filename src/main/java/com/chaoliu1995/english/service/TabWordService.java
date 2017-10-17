package com.chaoliu1995.english.service;

import com.chaoliu1995.english.base.BaseService;
import com.chaoliu1995.english.entity.shanbay.TabWord;
import com.chaoliu1995.english.model.ShanBayResult;
import com.chaoliu1995.english.util.Pager;

public interface TabWordService extends BaseService<TabWord> {
	
	/**
	 * 保存单词信息
	 * @param sbr
	 * @param savePath
	 */
	void saveWord(ShanBayResult sbr,String savePath);
	
	/**
	 * 分页获取单词信息
	 * @param currentPage
	 * @param pageSize
	 * @param recordTotal
	 * @return
	 */
	Pager<TabWord> listTabWordForExcel(Integer currentPage,Integer pageSize,Integer recordTotal);
	
	/**
	 * ESC排序operateTotal字段，获取第一个单词
	 * @return
	 */
	TabWord getTabWordByOperateTotalOrderEsc();
	
	/**
	 * 记忆一个单词，operateTotal++
	 * @param wordId
	 */
	void memory(Integer wordId);
}
