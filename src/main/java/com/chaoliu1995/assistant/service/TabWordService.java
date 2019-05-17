package com.chaoliu1995.assistant.service;

import com.chaoliu1995.assistant.dto.*;
import com.chaoliu1995.assistant.entity.shanbay.TabWord;
import com.chaoliu1995.assistant.model.ShanBayResult;

public interface TabWordService {

	/**
	 * 保存单词信息
	 * @param sbr
	 * @return
	 */
	TabWord saveWord(ShanBayResult sbr);

	/**
	 * 随机获取一个指定用户，指定书籍的待复习的单词
	 * @param commonIdDTO
	 * @param resultDTO
	 */
	void getWaitReviewWord(CommonIdDTO commonIdDTO, ResultDTO<WaitReviewDTO> resultDTO);

	/**
	 * 记忆一个单词，showTime 增加
	 * @param wordMemoryDTO
	 */
	void memory(WordMemoryDTO wordMemoryDTO);

	/**
	 * 分页获取单词集合，根据 实体属性过滤
	 * @param resultsDTO
	 * @param listDTO
	 */
	void listTabWord(ResultsDTO<TabWord> resultsDTO, CommonListDTO listDTO);

	/**
	 * 搜索单词
	 * @param word
	 * @param resultDTO
	 * @param userId
	 */
	void search(String word, ResultDTO<TabWord> resultDTO, Integer userId);

	/**
	 * 查询单词
	 * @param word
	 * @param resultDTO
	 */
	void getWord(String word, ResultDTO<TabWord> resultDTO);
}
