package com.chaoliu1995.assistant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2019/4/23 15:19
 */
@Data
@ApiModel(value = "WechatMiniProgramLoginDTO",description = "微信小程序登录信息DTO")
public class WeChatMiniProgramLoginDTO {
    @ApiModelProperty(name = "jsCode", value = "微信小程序登录code", required = true, dataType = "string")
    private String jsCode;
}
