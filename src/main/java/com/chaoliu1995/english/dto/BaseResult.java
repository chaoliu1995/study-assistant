package com.chaoliu1995.english.dto;

import com.chaoliu1995.english.util.Consts;
import lombok.Data;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/5 14:14
 */
@Data
public class BaseResult {
    public BaseResult(){
        this.status = Consts.ERROR;
    }
    protected String status;
    protected String message;
}
