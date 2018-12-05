package com.chaoliu1995.english.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/5 15:10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ListBookDTO extends BasePager {
    private String name;
}
