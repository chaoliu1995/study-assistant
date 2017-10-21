package com.chaoliu1995.english.service;

import java.util.Map;

import com.chaoliu1995.english.base.BaseService;
import com.chaoliu1995.english.entity.User;

/** 
* @Author: ChaoLiu
* @Description: 
* @Email: chaoliu1995@qq.com
* @CreateDate: 2017年10月21日 下午7:48:41
*/
public interface LoginService extends BaseService<User> {
	
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	Map<String,String> login(User user,Map<String, String> resultMap);
	
}
