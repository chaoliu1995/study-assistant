package com.chaoliu1995.english.dao;

import com.chaoliu1995.english.dto.SearchListDTO;
import com.chaoliu1995.english.dto.WordMemoryDTO;
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
	 * 随机获取一个出现时间小于当前时间的单词
	 * @return
	 */
	TabWord getByShowTime();

	/**
	 * 指定书籍，随机获取一个出现时间小于当前时间的单词
	 * @return
	 */
	TabWord getByBookIdAndShowTime(@Param("bookIds") String bookIds);

	/**
	 * 查询待复习单词总数
	 * @return
	 */
	int countByBookIdAndShowTime(@Param("bookIds") String bookIds);

	/**
	 * 记忆一个单词
	 * @param wordMemoryDTO
	 * @return
	 */
	int memory(@Param("wordMemoryDTO")WordMemoryDTO wordMemoryDTO);

	/**
	 * 查询待复习单词总数
	 * @return
	 */
	int countForWaitReview();
}
