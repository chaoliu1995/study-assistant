package com.chaoliu1995.assistant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/7/4 15:08
 */
@Data
@ApiModel(value = "WordMemoryDTO",description = "")
public class WordMemoryDTO {
    @NotNull(message = "单词id不可以为空")
    @ApiModelProperty(name = "wordId", value = "单词id", required = true, dataType = "integer")
    private Integer wordId;
    @Range(min = 1,max = 365,message = "此参数值必须在1-365之间")
    @ApiModelProperty(name = "bookId", value = "记忆状态(几天后再复习)", required = true, dataType = "integer")
    private Integer memoryStatus;
    private Long nextShowTime;
    private Integer userId;
}
