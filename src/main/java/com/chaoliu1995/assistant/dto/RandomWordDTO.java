package com.chaoliu1995.assistant.dto;

import lombok.Data;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2019/5/21 18:35
 */
@Data
public class RandomWordDTO {
    private Integer bookId;
    private Integer wordId;
    private Integer status; // 等于 10 走默认，小于 10 自定义，大于 10 则 365

    private Integer interval;
    private String intervalType;

    private Integer userId;
}
