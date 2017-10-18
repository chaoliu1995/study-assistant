package com.chaoliu1995.english.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.chaoliu1995.english.base.BaseJunit4Test;
import com.chaoliu1995.english.entity.shanbay.TabWord;
import com.chaoliu1995.english.util.Consts;

/** 
* @Author: LiuChao
* @Description: 
* @Email: chaoliu1995@QQ.com
* @CreateDate: 2017年10月17日 下午7:20:17
*/
public class TabWordMapperTest extends BaseJunit4Test {
	
	@Autowired
	private TabWordMapper tabWordMapper;
	
	@Test
	public void getByOperateTotalOrderEscTest(){
		TabWord word = tabWordMapper.getByMemoryTotalOrderEsc();
		Assert.notNull(word, "或许是没有数据");
	}
	
	@Test
	public void memoryTest(){
		tabWordMapper.memory(59,Consts.ORDINARY);
	}
	
}
