package com.chaoliu1995.english.dao;

import com.chaoliu1995.english.entity.shanbay.UsAudioAddresses;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component("usAudioAddressesMapper")
public interface UsAudioAddressesMapper extends Mapper<UsAudioAddresses> {
	
}
