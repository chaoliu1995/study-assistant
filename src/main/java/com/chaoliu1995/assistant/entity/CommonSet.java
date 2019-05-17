package com.chaoliu1995.assistant.entity;

import com.chaoliu1995.assistant.util.AbstractUser;
import com.chaoliu1995.assistant.util.Consts;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: ChaoLiu
 * @Description: 通用集合实体
 * @Date: 2019/4/16 11:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommonSet extends AbstractUser implements Serializable {

    private static final long serialVersionUID = Consts.SERIAL_VERSION_UID;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer typeKey;
    private String name;
    private Date createTime;

    public CommonSet(Integer typeKey,Integer userId,String name){
        super();
        this.typeKey = typeKey;
        this.userId = userId;
        this.name = name;
    }

    public CommonSet(){}
}
