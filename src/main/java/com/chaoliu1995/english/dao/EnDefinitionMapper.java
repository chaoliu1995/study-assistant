package com.chaoliu1995.english.dao;

import com.chaoliu1995.english.entity.shanbay.EnDefinition;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component("enDefinitionMapper")
public interface EnDefinitionMapper extends Mapper<EnDefinition> {

}
