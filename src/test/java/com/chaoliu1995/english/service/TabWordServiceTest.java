package com.chaoliu1995.english.service;

import com.chaoliu1995.english.EnglishApplicationTest;
import com.chaoliu1995.english.dao.UserWordMapper;
import com.chaoliu1995.english.dto.*;
import com.chaoliu1995.english.entity.UserWord;
import com.chaoliu1995.english.entity.shanbay.TabWord;
import com.chaoliu1995.english.util.Consts;
import com.chaoliu1995.english.util.ConstsTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/7/5 14:01
 */
public class TabWordServiceTest extends EnglishApplicationTest {

    @Autowired
    private TabWordService tabWordService;

    @Autowired
    private UserWordMapper userWordMapper;

    @Test
    public void testSearch(){
        String word = "simulation";
        ResultDTO<TabWord> resultDTO = new ResultDTO<>();
        resultDTO.setStatus(Consts.ERROR);
        tabWordService.search(word,resultDTO,ConstsTest.USER_ID);
        Assert.assertTrue(resultDTO.getStatus().equals(Consts.SUCCESS));
    }

    @Test
    public void testGetWaitReviewWord(){
        ReviewWordDTO reviewWordDTO = new ReviewWordDTO();
        reviewWordDTO.setUserId(ConstsTest.USER_ID);
        ResultDTO<WaitReviewDTO> resultDTO = new ResultDTO<>();
        tabWordService.getWaitReviewWord(reviewWordDTO,resultDTO);
        Assert.assertTrue(resultDTO.getStatus().equals(Consts.SUCCESS));
        reviewWordDTO.setBookId(ConstsTest.BOOK_ID);
        resultDTO = new ResultDTO<>();
        tabWordService.getWaitReviewWord(reviewWordDTO,resultDTO);
        Assert.assertTrue(resultDTO.getStatus().equals(Consts.SUCCESS));
    }

    @Test
    public void testMemory(){
        WordMemoryDTO wordMemoryDTO = new WordMemoryDTO();
        wordMemoryDTO.setUserId(ConstsTest.USER_ID);
        wordMemoryDTO.setMemoryStatus(1);
        wordMemoryDTO.setNextShowTime(System.currentTimeMillis() / 1000 + (86400 * wordMemoryDTO.getMemoryStatus()));
        wordMemoryDTO.setWordId(ConstsTest.WORD_ID);
        tabWordService.memory(wordMemoryDTO);
        UserWord userWord = new UserWord(ConstsTest.USER_ID,ConstsTest.WORD_ID);
        userWord = userWordMapper.selectOne(userWord);
        Assert.assertTrue(userWord.getShowTime() != null);
    }

    @Test
    public void testGetWord(){
        String word = "umbrella";
        ResultDTO<TabWord> resultDTO = new ResultDTO<>();
        tabWordService.getWord(word,resultDTO);
        Assert.assertTrue(resultDTO.getStatus().equals(Consts.SUCCESS));
    }

    @Test
    public void testListTabWordForPager(){
        ResultsDTO<TabWord> resultsDTO = new ResultsDTO<>();
        SearchListDTO searchListDTO = new SearchListDTO();
        searchListDTO.setPage(1);
        searchListDTO.setRows(10);
        searchListDTO.setUserId(ConstsTest.USER_ID);
        tabWordService.listTabWordForPager(resultsDTO,searchListDTO);
        Assert.assertTrue(resultsDTO.getStatus().equals(Consts.SUCCESS));
    }

}
