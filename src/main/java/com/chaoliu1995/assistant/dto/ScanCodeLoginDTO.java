package com.chaoliu1995.assistant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2019/5/14 13:41
 */
@Data
@ApiModel(value = "ScanCodeLoginDTO",description = "扫码登录参数DTO")
public class ScanCodeLoginDTO {
    @ApiModelProperty(name = "loginCode", value = "扫码登录code", required = true, dataType = "string")
    private String loginCode;
}
