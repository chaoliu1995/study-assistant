package com.chaoliu1995.english.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/12 11:46
 */
@Data
@ApiModel(value = "WechatMiniProgramLoginDTO",description = "微信小程序登录信息DTO")
public class WechatMiniProgramLoginDTO {
    @NotBlank(message = "参数不可以为空")
    @ApiModelProperty(name = "jsCode", value = "微信小程序登录code", required = true, dataType = "string")
    private String jsCode;
}
