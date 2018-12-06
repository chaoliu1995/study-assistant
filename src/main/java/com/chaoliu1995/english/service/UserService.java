package com.chaoliu1995.english.service;

import com.chaoliu1995.english.dto.BaseResult;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/6 14:42
 */
public interface UserService {

    /**
     * 修改正在复习的书籍
     * @param userId
     * @param bookId
     */
    void updateReviewingBook(Integer userId, Integer bookId, BaseResult result);

    /**
     * 修改正在添加单词的书籍
     * @param userId
     * @param bookId
     */
    void updateAddingBook(Integer userId, Integer bookId, BaseResult result);

}
