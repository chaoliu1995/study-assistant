package com.chaoliu1995.english.service;

import com.chaoliu1995.english.EnglishApplication;
import com.chaoliu1995.english.dto.ResultDTO;
import com.chaoliu1995.english.entity.shanbay.TabWord;
import com.chaoliu1995.english.model.ShanBayResult;
import com.chaoliu1995.english.util.Consts;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/7/5 14:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TabWordServiceTest {

    @Autowired
    private TabWordService tabWordService;

    @Test
    public void testSearch(){
        String word = "umbrella";
        ResultDTO<TabWord> resultDTO = new ResultDTO<TabWord>();
        resultDTO.setStatus(Consts.ERROR);
        tabWordService.search(word,resultDTO);
        Assert.assertTrue(resultDTO.getStatus().equals(Consts.SUCCESS));
    }

    @Test
    public void testRequestShanBay(){
        String word = "umbrella";
        ShanBayResult shanBayResult = tabWordService.requestShanBay(word);
        Assert.assertNotNull(shanBayResult);
    }
}
