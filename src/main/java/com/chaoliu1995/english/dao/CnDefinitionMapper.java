package com.chaoliu1995.english.dao;

import com.chaoliu1995.english.entity.shanbay.CnDefinition;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component("cnDefinitionMapper")
public interface CnDefinitionMapper extends Mapper<CnDefinition> {

}
