package com.chaoliu1995.assistant.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/10/31 15:12
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ResultDTO<T> extends BaseResult {
    private T data;
}
