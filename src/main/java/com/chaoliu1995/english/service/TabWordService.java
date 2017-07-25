package com.chaoliu1995.english.service;

import com.chaoliu1995.english.base.BaseService;
import com.chaoliu1995.english.entity.shanbay.TabWord;
import com.chaoliu1995.english.model.ShanBayResult;

public interface TabWordService extends BaseService<TabWord> {
	
	/**
	 * 保存单词信息
	 * @param sbr
	 */
	void saveWord(ShanBayResult sbr,String savePath);
	
}
