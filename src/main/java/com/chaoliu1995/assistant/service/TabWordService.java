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
	 * 随机获取一个指定用户，指定单词本的待复习的单词
	 * @param randomWordDTO
	 * @param resultDTO
	 */
	void randomWord(RandomWordDTO randomWordDTO, ResultDTO<WaitReviewDTO> resultDTO);

	/**
	 * 分页获取单词集合，根据 实体属性过滤
	 * @param resultsDTO
	 * @param listDTO
	 */
	void listTabWord(ResultsDTO<TabWord> resultsDTO, CommonListDTO listDTO);

	/**
	 * 搜索单词
	 * @param searchDTO
	 * @param resultDTO
	 */
	void search(SearchDTO searchDTO, ResultDTO<TabWord> resultDTO);

	/**
	 * 查询单词
	 * @param searchDTO
	 * @param resultDTO
	 */
	void getWord(SearchDTO searchDTO, ResultDTO<TabWord> resultDTO);
}
