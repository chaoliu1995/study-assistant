package com.chaoliu1995.assistant.dto;

import com.chaoliu1995.assistant.util.Consts;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/11/10 20:13
 */
@Data
@NoArgsConstructor
@ApiModel(value = "LoginDTO",description = "登录信息DTO")
public class LoginDTO implements Serializable {

    private static final long serialVersionUID = Consts.SERIAL_VERSION_UID;
    @NotBlank(message = "用户名不可以为空")
    @ApiModelProperty(name = "username", value = "登录名", required = true, dataType = "string")
    private String username;
    @NotBlank(message = "密码不可以为空")
    @ApiModelProperty(name = "password", value = "密码", required = true, dataType = "string")
    private String password;
    @NotBlank(message = "验证码不可以为空")
    @ApiModelProperty(name = "verifyCode", value = "验证码", required = true, dataType = "string")
    private String verifyCode;
    @NotNull(message = "remember 不可以为空")
    @ApiModelProperty(name = "rememberMe", value = "是否记住登录状态", required = true, dataType = "boolean")
    private Boolean rememberMe;
}
