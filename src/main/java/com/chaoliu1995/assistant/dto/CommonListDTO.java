package com.chaoliu1995.assistant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2019/4/15 19:11
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CommonListDTO",description = "通用分页DTO")
public class CommonListDTO extends BasePager {
    @Length(max = 50,message = "查询关键字最长为30个字符")
    @ApiModelProperty(name = "keyword", value = "关键字", dataType = "string")
    private String keyword;
    @ApiModelProperty(name = "startTime", value = "开始时间", dataType = "string")
    private String startTime;
    @ApiModelProperty(name = "endTime", value = "结束时间", dataType = "string")
    private String endTime;
    private Integer userId;
}
