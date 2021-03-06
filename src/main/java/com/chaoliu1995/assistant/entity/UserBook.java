package com.chaoliu1995.assistant.entity;

import com.chaoliu1995.assistant.util.Consts;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Author: ChaoLiu
 * @Description: 用户书籍关联实体
 * @Date: 2018/12/6 18:23
 */
@Data
public class UserBook implements Serializable {

    private static final long serialVersionUID = Consts.SERIAL_VERSION_UID;

    public UserBook(Integer bookId){
        this.bookId = bookId;
    }

    public UserBook(Integer userId, Integer bookId){
        this.userId = userId;
        this.bookId = bookId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer bookId;
    private Byte status;
}
