package com.chaoliu1995.assistant.service;

import com.chaoliu1995.assistant.dto.BaseResult;
import com.chaoliu1995.assistant.dto.CommonIdDTO;
import com.chaoliu1995.assistant.dto.PasswordUpdateDTO;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/6 14:42
 */
public interface UserService {

    /**
     * 修改正在复习的书籍
     * @param commonIdDTO
     * @param result
     */
    void updateCurrentBook(CommonIdDTO commonIdDTO, String type, BaseResult result);

    /**
     * 修改密码
     * @param updateDTO
     * @param result
     */
    void updatePassword(PasswordUpdateDTO updateDTO, BaseResult result);

}

