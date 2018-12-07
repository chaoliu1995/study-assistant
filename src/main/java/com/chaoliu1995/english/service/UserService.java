package com.chaoliu1995.english.service;

import com.chaoliu1995.english.dao.UpdateCurrentBookDTO;
import com.chaoliu1995.english.dto.BaseResult;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/6 14:42
 */
public interface UserService {

    /**
     * 修改正在复习的书籍
     * @param updateDTO
     * @param result
     */
    void updateCurrentBook(UpdateCurrentBookDTO updateDTO, String type, BaseResult result);

}
