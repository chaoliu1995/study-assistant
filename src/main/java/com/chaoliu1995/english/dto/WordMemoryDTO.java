package com.chaoliu1995.english.dto;

import com.chaoliu1995.english.util.Consts;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/7/4 15:08
 */
@Data
@NoArgsConstructor
public class WordMemoryDTO implements Serializable {
    private static final long serialVersionUID = Consts.SERIAL_VERSION_UID;
    private Integer wordId;
    private Integer memoryStatus;
    private Long nextShowTime;
}
