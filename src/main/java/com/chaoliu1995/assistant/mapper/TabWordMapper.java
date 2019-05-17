package com.chaoliu1995.assistant.mapper;

import com.chaoliu1995.assistant.dto.CommonListDTO;
import com.chaoliu1995.assistant.entity.shanbay.TabWord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component("tabWordMapper")
public interface TabWordMapper extends Mapper<TabWord> {

	/**
	 * 分页获取单词列表
	 * @param listDTO
	 * @return
	 */
	List<TabWord> listByCommonListDTO(@Param("listDTO") CommonListDTO listDTO);

	/**
	 * 根据条件，统计单词总数
	 * @param listDTO
	 * @return
	 */
	Integer countByCommonListDTO(@Param("listDTO") CommonListDTO listDTO);

}
