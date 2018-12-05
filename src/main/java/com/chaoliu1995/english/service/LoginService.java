package com.chaoliu1995.english.service;

import com.chaoliu1995.english.dto.BaseResult;
import com.chaoliu1995.english.dto.LoginDTO;

/** 
* @Author: ChaoLiu
* @Description: 
* @Email: chaoliu1995@qq.com
* @CreateDate: 2017年10月21日 下午7:48:41
*/
public interface LoginService {
	
	/**
	 * 登录
	 * @param loginDTO
	 * @return
	 */
    void login(LoginDTO loginDTO, BaseResult result);
	
}
