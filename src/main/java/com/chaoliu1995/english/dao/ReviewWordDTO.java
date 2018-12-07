package com.chaoliu1995.english.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/7 14:53
 */
@Data
@ApiModel(value = "ReviewWordDTO",description = "")
public class ReviewWordDTO {
    @ApiModelProperty(name = "bookId", value = "书籍id", required = false, dataType = "integer")
    private Integer bookId;
    private Integer userId;
}
