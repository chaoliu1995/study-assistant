package com.chaoliu1995.english.dto;

import com.chaoliu1995.english.util.Consts;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/8/15 17:06
 */
@Data
@NoArgsConstructor
public class PagerResultDTO<T> implements Serializable {

    private static final long serialVersionUID = Consts.SERIAL_VERSION_UID;

    private List<T> data;
    private Integer total;
}
