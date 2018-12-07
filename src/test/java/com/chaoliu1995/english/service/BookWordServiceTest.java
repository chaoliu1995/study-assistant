package com.chaoliu1995.english.service;

import com.chaoliu1995.english.EnglishApplicationTest;
import com.chaoliu1995.english.dto.*;
import com.chaoliu1995.english.entity.Book;
import com.chaoliu1995.english.util.Consts;
import com.chaoliu1995.english.util.ConstsTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/7 9:47
 */
public class BookWordServiceTest extends EnglishApplicationTest {

    @Autowired
    private BookWordService bookWordService;

    @Test
    public void testAddBook(){
        String name = "初三";
        ResultDTO<Book> resultDTO = new ResultDTO<>();
        bookWordService.addBook(name,ConstsTest.USER_ID,resultDTO);
        Assert.assertTrue(resultDTO.getStatus().equals(Consts.SUCCESS));
    }

    @Test
    public void testAddWord(){
        InsertBookWordDTO insertBookWordDTO = new InsertBookWordDTO();
        BaseResult result = new ResultDTO<>();
        insertBookWordDTO.setBookId(2);
        insertBookWordDTO.setWordId(ConstsTest.WORD_ID);
        bookWordService.addWord(insertBookWordDTO,ConstsTest.USER_ID,result);
        Assert.assertTrue(result.getStatus().equals(Consts.SUCCESS));
    }

    @Test
    public void testListBook(){
        ListBookDTO listDTO = new ListBookDTO();
        ResultsDTO<Book> resultsDTO = new ResultsDTO<>();
        listDTO.setUserId(ConstsTest.USER_ID);
        listDTO.setPage(1);
        listDTO.setRows(10);
        bookWordService.listBook(listDTO,resultsDTO);
        Assert.assertTrue(resultsDTO.getStatus().equals(Consts.SUCCESS));
    }
}
