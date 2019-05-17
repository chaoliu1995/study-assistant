package com.chaoliu1995.assistant.entity;

import com.chaoliu1995.assistant.util.Consts;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/6 10:19
 */
@Data
public class UserWord implements java.io.Serializable {

    private static final long serialVersionUID = Consts.SERIAL_VERSION_UID;

    public UserWord(Integer userId, Integer wordId){
        this.userId = userId;
        this.wordId = wordId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer wordId;
    private Integer searchCount;
    private Long showTime;
}
