package com.chaoliu1995.english.service.impl;

import com.chaoliu1995.english.dao.BookMapper;
import com.chaoliu1995.english.dao.BookWordMapper;
import com.chaoliu1995.english.dao.TabWordMapper;
import com.chaoliu1995.english.dto.*;
import com.chaoliu1995.english.entity.Book;
import com.chaoliu1995.english.entity.BookWord;
import com.chaoliu1995.english.entity.shanbay.TabWord;
import com.chaoliu1995.english.service.BookWordService;
import com.chaoliu1995.english.util.Consts;
import com.chaoliu1995.english.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/5 14:49
 */
@Service("bookWordService")
public class BookWordServiceImpl implements BookWordService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookWordMapper bookWordMapper;

    @Autowired
    private TabWordMapper tabWordMapper;

    @Override
    public void add(String name, Integer userId, ResultDTO<Book> resultDTO) {
        Book book = bookMapper.getByNameAndUserId(name,userId);
        if(book != null){
            resultDTO.setMessage("书籍名称已存在");
            return;
        }
        book = new Book();
        book.setName(name);
        book.setUserId(userId);
        bookMapper.insert(book);
        resultDTO.setStatus(Consts.SUCCESS);
    }

    @Override
    public void addWord(InsertBookWordDTO insertBookWordDTO, Integer userId, BaseResult result) {
        Book book = bookMapper.selectByPrimaryKey(insertBookWordDTO.getBookId());
        if(book == null){
            result.setMessage("书籍不存在");
            return;
        }
        if((book.getUserId() - userId) != 0){
            result.setMessage("当前用户不是此书籍的创建者，禁止添加单词");
            return;
        }
        TabWord tabWord = tabWordMapper.selectByPrimaryKey(insertBookWordDTO.getWordId());
        if(tabWord == null){
            result.setMessage("单词不存在");
            return;
        }
        BookWord bookWord = new BookWord();
        bookWord.setBookId(book.getId());
        bookWord.setWordId(tabWord.getId());
        BookWord tempBookWord = bookWordMapper.selectOne(bookWord);
        if(tempBookWord != null){
            result.setMessage("单词已存在");
            return;
        }
        bookWordMapper.insert(bookWord);
        result.setStatus(Consts.SUCCESS);
    }

    @Override
    public void listBook(ListBookDTO listDTO, ResultsDTO<Book> resultsDTO) {
        int total = bookMapper.countByListBookDTO(listDTO);
        Pager pager = new Pager(listDTO.getPage(),listDTO.getRows(),total);
        listDTO.setPage(pager.getCurrentPage());
        listDTO.setRows(pager.getPageSize());
        listDTO.setStart(pager.getStartNum());
        List<Book> list = bookMapper.listByListBookDTO(listDTO);
        resultsDTO.setData(list);
        resultsDTO.setTotal(total);
        resultsDTO.setStatus(Consts.SUCCESS);
    }

    @Override
    public Book getBookById(Integer bookId) {
        return bookMapper.selectByPrimaryKey(bookId);
    }
}
