package com.chaoliu1995.english.dto;

import com.chaoliu1995.english.util.Consts;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: ChaoLiu
 * @Description: 登录DTO
 * @Date: 2018/8/24 15:41
 */
@Data
@NoArgsConstructor
@ApiModel(value = "LoginDTO",description = "登录信息DTO")
public class LoginDTO implements Serializable {

    private static final long serialVersionUID = Consts.SERIAL_VERSION_UID;
    @ApiModelProperty(name = "username", value = "登录名", required = true, dataType = "string")
    private String username;
    @ApiModelProperty(name = "password", value = "密码", required = true, dataType = "string")
    private String password;
    @ApiModelProperty(name = "verifyCode", value = "验证码", required = true, dataType = "string")
    private String verifyCode;
}
