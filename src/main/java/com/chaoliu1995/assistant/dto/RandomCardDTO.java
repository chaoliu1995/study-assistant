package com.chaoliu1995.assistant.dto;

import lombok.Data;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2019/4/24 14:00
 */
@Data
public class RandomCardDTO {
    private Integer cardBagId;
    private Integer memoryCardId;
    private Integer status; // 等于 10 走默认，小于 10 自定义，大于 10 则 365

    private Integer interval;
    private String intervalType;

    private Integer userId;
}
