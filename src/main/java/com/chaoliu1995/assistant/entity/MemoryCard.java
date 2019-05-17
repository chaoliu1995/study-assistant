package com.chaoliu1995.assistant.entity;

import com.chaoliu1995.assistant.util.Consts;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: ChaoLiu
 * @Description: 记忆卡片
 * @Date: 2019/4/15 15:43
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MemoryCard implements Serializable {

    private static final long serialVersionUID = Consts.SERIAL_VERSION_UID;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer cardBagId;
    private String question;
    private String answer;
    private Integer memoryStatus;
    private Date showTime;
    private Date createTime;
}
