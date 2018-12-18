package com.chaoliu1995.english.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/7/4 15:05
 */
@Data
public class SearchDTO {
    @Pattern(regexp = "^[A-Za-z]+$",message = "请输入正确的英文单词")
    @Size(min = 1,max = 100,message = "你确定有超过100个字母的单词吗？")
    private String word;
}
