package com.chaoliu1995.english.service;

import com.chaoliu1995.english.EnglishApplicationTest;
import com.chaoliu1995.english.dao.UpdateCurrentBookDTO;
import com.chaoliu1995.english.dto.BaseResult;
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
public class UserServiceTest extends EnglishApplicationTest {

    @Autowired
    private UserService userService;

    @Test
    public void testUpdateCurrentBook(){
        UpdateCurrentBookDTO updateDTO = new UpdateCurrentBookDTO();
        updateDTO.setUserId(ConstsTest.USER_ID);
        updateDTO.setBookId(ConstsTest.BOOK_ID);
        BaseResult result = new BaseResult();
        userService.updateCurrentBook(updateDTO,Consts.REVIEWING,result);
        Assert.assertTrue(result.getStatus().equals(Consts.SUCCESS));
        result = new BaseResult();
        userService.updateCurrentBook(updateDTO,Consts.ADDING,result);
        Assert.assertTrue(result.getStatus().equals(Consts.SUCCESS));
    }


}
