package com.chaoliu1995.assistant.service;

import com.chaoliu1995.assistant.dto.BaseResult;
import com.chaoliu1995.assistant.dto.LoginDTO;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/11/10 20:09
 */
public interface LoginService {

    /**
     * 登录
     * @param loginDTO
     * @return
     */
    void login(LoginDTO loginDTO, BaseResult resultDTO);

}
