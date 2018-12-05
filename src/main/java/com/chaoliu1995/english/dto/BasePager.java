package com.chaoliu1995.english.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/5 15:09
 */
@Data
@NoArgsConstructor
public class BasePager {
    @Min(value = 1,message = "当前页不可以小于 1")
    @ApiModelProperty(name = "page", value = "当前页", required = false, dataType = "integer")
    protected Integer page;
    @Range(min = 10,max = 50,message = "每页数据量必须在10~50之间")
    @ApiModelProperty(name = "rows", value = "每页数据量", required = false, dataType = "integer")
    protected Integer rows;
    protected Integer start;
}
