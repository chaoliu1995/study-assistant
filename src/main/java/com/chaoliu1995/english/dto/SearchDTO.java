package com.chaoliu1995.english.dto;

import com.chaoliu1995.english.util.Consts;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/7/4 15:05
 */
@Data
@NoArgsConstructor
public class SearchDTO implements Serializable {
    private static final long serialVersionUID = Consts.SERIAL_VERSION_UID;
    private String word;
}
