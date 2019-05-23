package com.chaoliu1995.assistant.entity;

import com.chaoliu1995.assistant.util.AbstractUser;
import com.chaoliu1995.assistant.util.Consts;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/6 10:19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserWord extends AbstractUser implements java.io.Serializable {

    private static final long serialVersionUID = Consts.SERIAL_VERSION_UID;

    public UserWord(Integer userId, Integer wordId){
        this.userId = userId;
        this.wordId = wordId;
    }

    public void init(){
        this.createTime = new Date();
        this.showTime = this.createTime;
        this.memoryStatus = Consts.MEMORY_STATUS_1;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer wordId;
    private Integer memoryStatus;
    private Date showTime;
    private Date createTime;
}
