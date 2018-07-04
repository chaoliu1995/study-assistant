package com.chaoliu1995.english.dto;

import com.chaoliu1995.english.util.Consts;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/7/4 11:16
 */
@Data
@NoArgsConstructor
public class ResultDTO<T> implements Serializable {

    private static final long serialVersionUID = Consts.SERIAL_VERSION_UID;

    private String status;
    private String message;
    private T data;

    public static final ResultDTO<Object> init(){
        ResultDTO<Object> result = new ResultDTO<Object>();
        result.setStatus(Consts.ERROR);
        return result;
    }
}
