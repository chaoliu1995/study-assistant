package com.chaoliu1995.assistant.entity;

import com.chaoliu1995.assistant.util.Consts;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author: ChaoLiu
 * @Description: 书籍和单词关联实体
 * @Date: 2018/12/5 14:05
 */
@Data
public class BookWord implements java.io.Serializable {

    private static final long serialVersionUID = Consts.SERIAL_VERSION_UID;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer bookId;
    private Integer wordId;

    public BookWord(Integer bookId){
        this.bookId = bookId;
    }

    public BookWord(Integer bookId,Integer wordId){
        this.bookId = bookId;
        this.wordId = wordId;
    }
}
