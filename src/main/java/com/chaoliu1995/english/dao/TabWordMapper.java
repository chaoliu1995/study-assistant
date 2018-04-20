package com.chaoliu1995.english.dao;

import com.chaoliu1995.english.entity.shanbay.TabWord;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

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
	List<TabWord> listTabWordForExcel(@Param("start")Integer start,@Param("limit")Integer limit,@Param("word")TabWord word);
	
	/**
	 * 分页获取单词列表
	 * @param start
	 * @param limit
	 * @param word
	 * @return
	 */
	List<TabWord> listByColForPager(@Param("start")Integer start,@Param("limit")Integer limit,@Param("word")TabWord word);
	
	/**
	 * 根据属性，统计单词总数
	 * @param word
	 * @return
	 */
	Integer countByCol(@Param("word")TabWord word);
	
	/**
	 * ESC排序memoryTotal字段，获取第一个单词
	 * @return
	 */
	TabWord getByMemoryTotalOrderEsc();
	
	/**
	 * 记忆一个单词，memoryTotal 增加
	 * @param wordId
	 */
	int memory(@Param("wordId")Integer wordId,@Param("num")byte num);
}
