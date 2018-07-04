package com.chaoliu1995.english.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/7/4 15:17
 */
public class PagerDTO {
    @Getter
    @Setter
    private Integer start;
    @Getter
    @Setter
    private Integer limit;
    @Getter
    @Setter
    private Integer currentPage;
    @Getter
    @Setter
    private Integer pageSize;
}
