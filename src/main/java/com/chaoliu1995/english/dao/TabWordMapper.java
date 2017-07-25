package com.chaoliu1995.english.dao;

import com.chaoliu1995.english.entity.shanbay.TabWord;

import tk.mybatis.mapper.common.Mapper;

public interface TabWordMapper extends Mapper<TabWord> {
	void insertReturnKey(TabWord tabWord);
}
