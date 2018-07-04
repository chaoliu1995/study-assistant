package com.chaoliu1995.english.dao;

import com.chaoliu1995.english.entity.User;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/** 
* @Author: ChaoLiu
* @Description: 
* @Email: chaoliu1995@qq.com
* @CreateDate: 2017年10月21日 下午7:47:29
*/
@Component("userMapper")
public interface UserMapper extends Mapper<User> {
	
}
