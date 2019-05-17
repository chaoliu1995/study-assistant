package com.chaoliu1995.assistant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2019/4/17 11:28
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ListMemoryCardDTO",description = "MemoryCard分页查询DTO")
public class ListMemoryCardDTO extends BasePager {
    @ApiModelProperty(name = "keyword", value = "关键字", dataType = "string")
    private String keyword;
    @NotNull(message = "必须选择一个卡包")
    @ApiModelProperty(name = "cardBagId", value = "卡包id", required = true, dataType = "integer")
    private String cardBagId;
    private Integer userId;
}
