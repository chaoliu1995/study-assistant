package com.chaoliu1995.english.dao;

import com.chaoliu1995.english.entity.shanbay.TabPronunciations;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component("tabPronunciationsMapper")
public interface TabPronunciationsMapper extends Mapper<TabPronunciations> {
	
}
