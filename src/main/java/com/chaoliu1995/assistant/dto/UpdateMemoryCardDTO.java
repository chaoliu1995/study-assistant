package com.chaoliu1995.assistant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2019/5/13 17:57
 */
@Data
@ApiModel(value = "UpdateMemoryCardDTO",description = "修改卡片数据DTO")
@EqualsAndHashCode(callSuper = false)
public class UpdateMemoryCardDTO extends UpdateDTO {
    @NotBlank(message = "问题不能为空")
    @ApiModelProperty(name = "question", value = "问题", required = true, dataType = "string")
    private String question;
    @NotBlank(message = "答案不能为空")
    @ApiModelProperty(name = "answer", value = "答案", required = true, dataType = "string")
    private String answer;
}
