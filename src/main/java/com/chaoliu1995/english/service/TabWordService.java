package com.chaoliu1995.english.service;

import com.chaoliu1995.english.dto.*;
import com.chaoliu1995.english.entity.shanbay.TabWord;
import com.chaoliu1995.english.model.ShanBayResult;

public interface TabWordService {
	
	/**
	 * 保存单词信息
	 * @param sbr
	 */
	void saveWord(ShanBayResult sbr);

	/**
	 * 随机获取一个待复习的单词
	 * @param resultDTO
	 */
	void getWaitReviewWord(ResultDTO<WaitReviewDTO> resultDTO);

	/**
	 * 记忆一个单词，memoryTotal 增加
	 * @param wordMemoryDTO
	 */
	void memory(WordMemoryDTO wordMemoryDTO);

	/**
	 * 分页获取单词集合，根据 实体属性过滤
	 * @param resultDTO
	 * @param searchListDTO
	 */
	void listTabWordForPager(ResultDTO<PagerResultDTO<TabWord>> resultDTO,SearchListDTO searchListDTO);

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
