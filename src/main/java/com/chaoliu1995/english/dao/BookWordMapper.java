package com.chaoliu1995.english.dao;

import com.chaoliu1995.english.entity.BookWord;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/5 14:29
 */
@Component("bookWordMapper")
public interface BookWordMapper extends Mapper<BookWord> {


}
