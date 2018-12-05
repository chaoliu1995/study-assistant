package com.chaoliu1995.english.entity;

import com.chaoliu1995.english.util.Consts;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/5 14:05
 */
@Data
public class Book implements java.io.Serializable {

    private static final long serialVersionUID = Consts.SERIAL_VERSION_UID;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String childIds;
}
