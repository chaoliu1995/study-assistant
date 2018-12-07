package com.chaoliu1995.english.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/7 15:08
 */
@Data
@ApiModel(value = "UpdateCurrentBookDTO",description = "修改当前在复习的书或在添加单词的书")
public class UpdateCurrentBookDTO {
    @ApiModelProperty(name = "bookId", value = "书籍id", required = true, dataType = "integer")
    private Integer bookId;
    private Integer userId;
}
