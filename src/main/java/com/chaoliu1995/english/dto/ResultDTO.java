package com.chaoliu1995.english.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/5 14:14
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ResultDTO<T> extends BaseResult {
    private T data;
}
