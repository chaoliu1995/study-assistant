package com.chaoliu1995.assistant.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/11/20 11:08
 */
@Data
@NoArgsConstructor
public class UpdateDTO {
    @NotNull(message = "id不可以为空")
    @ApiModelProperty(name = "id", value = "id", required = true, dataType = "integer")
    protected Integer id;
    protected Integer userId;
}
