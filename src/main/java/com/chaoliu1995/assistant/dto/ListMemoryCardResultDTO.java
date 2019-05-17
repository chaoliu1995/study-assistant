package com.chaoliu1995.assistant.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2019/4/15 19:17
 */
@Data
public class ListMemoryCardResultDTO {
    private Integer id;
    private String question;
    private Date createTime;
    private Date updateTime;
}
