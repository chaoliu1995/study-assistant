package com.chaoliu1995.english.dao;

import com.chaoliu1995.english.dto.SearchListDTO;
import com.chaoliu1995.english.entity.shanbay.TabWord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component("tabWordMapper")
public interface TabWordMapper extends Mapper<TabWord> {
	
	/**
	 * 插入单词时，返回主键
	 * @param tabWord
	 */
	void insertReturnKey(TabWord tabWord);

	/**
	 * 分页获取单词列表
	 * @param searchListDTO
	 * @return
	 */
	List<TabWord> listBySearchListDTO(@Param("searchListDTO")SearchListDTO searchListDTO);

	/**
	 * 根据条件，统计单词总数
	 * @param searchListDTO
	 * @return
	 */
	Integer countBySearchListDTO(@Param("searchListDTO")SearchListDTO searchListDTO);
	
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
