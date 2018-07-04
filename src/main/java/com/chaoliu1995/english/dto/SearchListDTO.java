package com.chaoliu1995.english.dto;

import com.chaoliu1995.english.util.Consts;

import java.io.Serializable;

/**
 * @Author: ChaoLiu
 * @Description: 翻页查询单词列表DTO
 * @Date: 2018/7/4 15:19
 */
public class SearchListDTO extends PagerDTO implements Serializable{
    private static final long serialVersionUID = Consts.SERIAL_VERSION_UID;
    private String word;
}
