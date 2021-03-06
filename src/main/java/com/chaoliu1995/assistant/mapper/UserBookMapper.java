package com.chaoliu1995.assistant.mapper;

import com.chaoliu1995.assistant.entity.UserBook;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/6 18:36
 */
@Component("userBookMapper")
public interface UserBookMapper extends Mapper<UserBook> {

}
