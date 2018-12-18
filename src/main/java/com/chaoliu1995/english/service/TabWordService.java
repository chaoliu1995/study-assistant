package com.chaoliu1995.english.service;

import com.chaoliu1995.english.dto.*;
import com.chaoliu1995.english.entity.shanbay.TabWord;
import com.chaoliu1995.english.model.ShanBayResult;

public interface TabWordService {

	/**
	 * 保存单词信息
	 * @param sbr
	 * @return
	 */
	TabWord saveWord(ShanBayResult sbr);

	/**
	 * 随机获取一个指定用户，指定书籍的待复习的单词
	 * @param reviewWordDTO
	 * @param resultDTO
	 */
	void getWaitReviewWord(ReviewWordDTO reviewWordDTO, ResultDTO<WaitReviewDTO> resultDTO);

	/**
	 * 记忆一个单词，showTime 增加
	 * @param wordMemoryDTO
	 */
	void memory(WordMemoryDTO wordMemoryDTO);

	/**
	 * 分页获取单词集合，根据 实体属性过滤
	 * @param resultsDTO
	 * @param searchListDTO
	 */
	void listTabWordForPager(ResultsDTO<TabWord> resultsDTO, SearchListDTO searchListDTO);

	/**
	 * 搜索单词
	 * @param word
	 * @param resultDTO
	 * @param userId
	 */
	void search(String word,ResultDTO<TabWord> resultDTO,Integer userId);

	/**
	 * 查询单词
	 * @param word
	 * @param resultDTO
	 */
	void getWord(String word, ResultDTO<TabWord> resultDTO);
}
