package com.chaoliu1995.assistant.service.impl;

import com.chaoliu1995.assistant.dto.BaseResult;
import com.chaoliu1995.assistant.dto.LoginDTO;
import com.chaoliu1995.assistant.entity.User;
import com.chaoliu1995.assistant.mapper.UserMapper;
import com.chaoliu1995.assistant.service.LoginService;
import com.chaoliu1995.assistant.util.Consts;
import com.chaoliu1995.assistant.util.security.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/11/10 20:11
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private PasswordUtils passwordUtils;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void login(LoginDTO loginDTO, BaseResult resultDTO) {
        logger.info("用户登录："+loginDTO.getUsername());
        try {
            User paramsUser = new User();
            if(loginDTO.getUsername().contains(Consts.EMAIL_MARK)){
                paramsUser.setEmail(loginDTO.getUsername());
            }else{
                paramsUser.setName(loginDTO.getUsername());
            }
            User dbUser = userMapper.selectOne(paramsUser);
            if(dbUser == null){
                resultDTO.setMessage("账号不存在");
                return;
            }
            //客户端密文AES解密
            String decryptPwd = passwordUtils.decryptAES(loginDTO.getPassword(), passwordUtils.getCLIENT_SKEY(), passwordUtils.getCLIENT_IVV());
            //服务器密文AES解密
            String serverPwd = passwordUtils.decryptAES(dbUser.getPassword(), passwordUtils.getSERVER_CRYPT_KEY(),passwordUtils.getSERVER_IV());
            if(serverPwd.equals(decryptPwd)){
                resultDTO.setStatus(Consts.SUCCESS);
                return;
            }
            resultDTO.setMessage("密码错误");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            resultDTO.setMessage("程序异常！请联系管理员");
        }
    }

}
