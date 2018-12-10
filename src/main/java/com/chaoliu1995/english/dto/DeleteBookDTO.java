package com.chaoliu1995.english.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/10 17:42
 */
@Data
@ApiModel(value = "DeleteBookDTO",description = "删除书籍DTO")
public class DeleteBookDTO {
    @NotNull(message = "书籍id不能为空")
    @ApiModelProperty(name = "bookId", value = "书籍id", required = true, dataType = "integer")
    private Integer bookId;
    private Integer userId;
}
