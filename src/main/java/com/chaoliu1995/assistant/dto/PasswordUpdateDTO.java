package com.chaoliu1995.assistant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2020/9/16 16:30
 */
@Data
@ApiModel(value = "PasswordUpdateDTO",description = "修改密码DTO")
public class PasswordUpdateDTO {
    @ApiModelProperty(name = "oldPassword", value = "旧密码", required = true, dataType = "string")
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;
    @ApiModelProperty(name = "newPassword", value = "新密码", required = true, dataType = "string")
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
    private Integer userId;
}
