package com.chaoliu1995.english.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chaoliu1995.english.entity.shanbay.TabWord;

import tk.mybatis.mapper.common.Mapper;

public interface TabWordMapper extends Mapper<TabWord> {
	
	/**
	 * 插入单词时，返回主键
	 * @param tabWord
	 */
	void insertReturnKey(TabWord tabWord);
	
	/**
	 * 分页获取单词列表
	 * @param startNum
	 * @param endNum
	 * @param word
	 * @return
	 */
	List<TabWord> listTabWordForPager(@Param("startNum")Integer startNum,@Param("limit")Integer limit,@Param("word")TabWord word);

	/**
	 * ESC排序operateTotal字段，获取第一个单词
	 * @return
	 */
	TabWord getByOperateTotalOrderEsc();
	
	/**
	 * 记忆一个单词，operateTotal++
	 * @param wordId
	 */
	int memory(@Param("wordId")Integer wordId);
}
