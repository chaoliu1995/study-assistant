package com.chaoliu1995.english.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/10 17:24
 */
@Data
@ApiModel(value = "AddBookDTO",description = "添加书籍DTO")
public class AddBookDTO {
    @NotBlank(message = "书籍名称不能为空")
    @ApiModelProperty(name = "name", value = "书籍名称", required = true, dataType = "string")
    private String name;
    private Integer userId;
}
