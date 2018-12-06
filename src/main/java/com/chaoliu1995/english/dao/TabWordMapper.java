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

}
