package com.chaoliu1995.english.service;

import com.chaoliu1995.english.dto.LoginDTO;
import com.chaoliu1995.english.dto.ResultDTO;
import com.chaoliu1995.english.entity.User;

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
    void login(LoginDTO loginDTO, ResultDTO<Object> resultDTO);
	
}
