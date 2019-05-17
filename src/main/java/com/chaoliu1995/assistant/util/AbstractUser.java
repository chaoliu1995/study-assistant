package com.chaoliu1995.assistant.util;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2019/4/16 18:14
 */
public abstract class AbstractUser {

    protected Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
