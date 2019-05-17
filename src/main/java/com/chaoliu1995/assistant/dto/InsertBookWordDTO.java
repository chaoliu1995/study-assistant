package com.chaoliu1995.assistant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/5 14:51
 */
@Data
@ApiModel(value = "InsertBookWordDTO",description = "添加书籍和单词的关联DTO")
public class InsertBookWordDTO {
    @NotNull(message = "书籍id不能为空")
    @ApiModelProperty(name = "bookId", value = "书籍id", required = true, dataType = "integer")
    private Integer bookId;
    @NotNull(message = "单词id不能为空")
    @ApiModelProperty(name = "wordId", value = "单词id", required = true, dataType = "integer")
    private Integer wordId;
    private Integer userId;
}
