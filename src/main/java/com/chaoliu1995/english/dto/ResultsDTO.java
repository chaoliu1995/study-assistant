package com.chaoliu1995.english.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/5 14:15
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ResultsDTO<T> extends BaseResult {
    private List<T> data;
    private Integer total;
}
