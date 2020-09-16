package com.chaoliu1995.assistant.dto;

import com.chaoliu1995.assistant.util.Consts;
import lombok.Data;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/10/31 15:15
 */
@Data
public class BaseResult {
    public BaseResult(){
        this.status = Consts.ERROR;
    }
    protected String status;
    protected String message;

    public void success(){
        this.status = Consts.SUCCESS;
    }
}
