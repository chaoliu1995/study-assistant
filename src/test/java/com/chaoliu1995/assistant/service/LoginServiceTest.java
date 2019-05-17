package com.chaoliu1995.assistant.service;

import com.chaoliu1995.assistant.AssistantApplicationTests;
import com.chaoliu1995.assistant.dto.BaseResult;
import com.chaoliu1995.assistant.dto.LoginDTO;
import com.chaoliu1995.assistant.util.ConstsTest;
import com.chaoliu1995.assistant.util.security.PasswordUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/11/13 17:09
 */
public class LoginServiceTest extends AssistantApplicationTests {

    @Autowired
    private LoginService loginService;

    @Autowired
    private PasswordUtils passwordUtils;

    @Test
    public void testLogin(){
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(ConstsTest.ERROR);
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("test_user");
        String password = "asd123456";
        try {
            password = passwordUtils.encryptMd5(password);
            password = passwordUtils.encrySha1(password);
            password = passwordUtils.encryptAES(password,passwordUtils.getCLIENT_SKEY(),passwordUtils.getCLIENT_IVV());
            loginDTO.setPassword(password);
        }catch (Exception e){
            e.printStackTrace();
        }
        loginService.login(loginDTO,baseResult);
        Assert.assertTrue(baseResult.getStatus().equals(ConstsTest.SUCCESS));
    }
}
