package com.chaoliu1995.assistant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2019/4/15 16:05
 */
@Data
@ApiModel(value = "AddMemoryCardDTO",description = "添加记忆卡片DTO")
public class AddMemoryCardDTO {
    private Integer userId;
    @NotBlank(message = "问题不能为空")
    @ApiModelProperty(name = "question", value = "问题", required = true, dataType = "string")
    private String question;
    @NotBlank(message = "答案不能为空")
    @ApiModelProperty(name = "answer", value = "答案", required = true, dataType = "string")
    private String answer;
    @NotNull(message = "必须选择一个卡包")
    @ApiModelProperty(name = "cardBagId", value = "卡包id", required = true, dataType = "integer")
    private Integer cardBagId;
}
