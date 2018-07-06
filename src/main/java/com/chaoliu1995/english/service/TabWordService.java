package com.chaoliu1995.english.service;

import com.chaoliu1995.english.dto.ResultDTO;
import com.chaoliu1995.english.dto.SearchListDTO;
import com.chaoliu1995.english.entity.shanbay.TabWord;
import com.chaoliu1995.english.model.ShanBayResult;
import com.chaoliu1995.english.util.Pager;

public interface TabWordService {
	
	/**
	 * 保存单词信息
	 * @param sbr
	 */
	void saveWord(ShanBayResult sbr);

	/**
	 * ESC排序operateTotal字段，获取第一个单词
	 * @return
	 */
	TabWord getTabWordByOperateTotalOrderEsc();
	
	/**
	 * 记忆一个单词，memoryTotal 增加
	 * @param wordId
	 * @param num
	 */
	void memory(Integer wordId,byte num);

    /**
     * 分页获取单词集合，根据 实体属性过滤
     * @param searchListDTO
     * @return
     */
	Pager<TabWord> listTabWordForPager(SearchListDTO searchListDTO);

	/**
	 * 搜索单词
	 * @param word
	 * @param resultDTO
	 * @return
	 */
	void search(String word,ResultDTO<TabWord> resultDTO);

	/**
	 * 请求ShanBayAPI查询单词
	 * @param word
	 * @return
	 */
	ShanBayResult requestShanBay(String word);
}
