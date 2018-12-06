package com.chaoliu1995.english.dao;

import com.chaoliu1995.english.dto.ListBookDTO;
import com.chaoliu1995.english.entity.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/5 14:28
 */
@Component("bookMapper")
public interface BookMapper extends Mapper<Book> {

    Book getByNameAndUserId(@Param("name") String name,@Param("userId") Integer userId);

    int countByListBookDTO(@Param("listDTO") ListBookDTO listDTO);

    List<Book> listByListBookDTO(@Param("listDTO") ListBookDTO listDTO);
}
