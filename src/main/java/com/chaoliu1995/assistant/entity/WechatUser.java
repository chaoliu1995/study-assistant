package com.chaoliu1995.assistant.entity;

import com.chaoliu1995.assistant.util.Consts;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/11/21 16:04
 */
@Data
@NoArgsConstructor
public class WechatUser implements Serializable {

    private static final long serialVersionUID = Consts.SERIAL_VERSION_UID;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String openId;
    private String unionId;
    private Integer userId;
    private Date createTime;
}
