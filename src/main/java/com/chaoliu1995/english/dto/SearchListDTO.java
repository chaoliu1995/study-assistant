package com.chaoliu1995.english.dto;

import com.chaoliu1995.english.util.Consts;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: ChaoLiu
 * @Description: 翻页查询单词列表DTO
 * @Date: 2018/7/4 15:19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SearchListDTO extends BasePager {
    private String word;
    private Integer userId;
}
