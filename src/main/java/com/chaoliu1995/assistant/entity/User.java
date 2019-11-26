package com.chaoliu1995.assistant.entity;

import com.chaoliu1995.assistant.util.Consts;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: ChaoLiu
 * @Description: 用户信息实体
 * @Email: chaoliu1995@qq.com
 * @CreateDate: 2017年10月21日 下午7:43:00
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = Consts.SERIAL_VERSION_UID;

    public User(){}

    public User(String email){
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String password;
    private String email;
    private Integer status;
    private Date createTime;
    private Integer reviewingBookId;
    private Integer addingBookId;
    private Integer pwdErrorNum;
}