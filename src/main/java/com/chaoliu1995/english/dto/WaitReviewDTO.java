package com.chaoliu1995.english.dto;

import com.chaoliu1995.english.entity.shanbay.TabWord;
import com.chaoliu1995.english.util.Consts;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: ChaoLiu
 * @Description: 等待复习的单词信息
 * @Date: 2018/8/24 14:10
 */
@Data
@NoArgsConstructor
public class WaitReviewDTO implements Serializable {
    private static final long serialVersionUID = Consts.SERIAL_VERSION_UID;
    private TabWord word;
    private Integer total;
}
