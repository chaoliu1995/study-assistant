package com.chaoliu1995.english.service;

import com.chaoliu1995.english.dto.*;
import com.chaoliu1995.english.entity.Book;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/5 14:48
 */
public interface BookWordService {

    /**
     * 添加书籍
     * @param addBookDTO
     * @param resultDTO
     */
    void addBook(AddBookDTO addBookDTO, ResultDTO<Book> resultDTO);

    /**
     * 删除书籍
     * @param deleteBookDTO
     * @param result
     */
    void deleteBook(DeleteBookDTO deleteBookDTO,BaseResult result);

    /**
     * 添加书籍和单词的关联
     * @param insertBookWordDTO
     * @param result
     */
    void addWord(InsertBookWordDTO insertBookWordDTO, BaseResult result);

    /**
     * 分页获取书籍数据
     * @param listDTO
     * @param resultsDTO
     */
    void listBook(ListBookDTO listDTO, ResultsDTO<Book> resultsDTO);

    /**
     * 根据id查询书籍信息
     * @param bookId
     * @return
     */
    Book getBookById(Integer bookId);
}
