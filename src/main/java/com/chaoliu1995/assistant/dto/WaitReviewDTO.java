package com.chaoliu1995.assistant.dto;

import com.chaoliu1995.assistant.entity.shanbay.TabWord;
import com.chaoliu1995.assistant.util.Consts;
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
    /**
     * 待复习单词总数
     */
    private Integer total;
}
