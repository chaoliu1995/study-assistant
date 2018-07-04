package com.chaoliu1995.english.dao;

import com.chaoliu1995.english.entity.shanbay.UkAudioAddresses;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component("ukAudioAddressesMapper")
public interface UkAudioAddressesMapper extends Mapper<UkAudioAddresses> {
	
}
