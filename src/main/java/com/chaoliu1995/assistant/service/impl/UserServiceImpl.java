package com.chaoliu1995.assistant.service.impl;


import com.chaoliu1995.assistant.dto.BaseResult;
import com.chaoliu1995.assistant.dto.CommonIdDTO;
import com.chaoliu1995.assistant.dto.PasswordUpdateDTO;
import com.chaoliu1995.assistant.entity.CommonSet;
import com.chaoliu1995.assistant.entity.User;
import com.chaoliu1995.assistant.mapper.CommonSetMapper;
import com.chaoliu1995.assistant.mapper.UserMapper;
import com.chaoliu1995.assistant.service.UserService;
import com.chaoliu1995.assistant.util.Consts;
import com.chaoliu1995.assistant.util.security.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/6 14:43
 */
@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private CommonSetMapper commonSetMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordUtils passwordUtils;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCurrentBook(CommonIdDTO commonIdDTO, String type, BaseResult result) {
        if(commonIdDTO.getCommonId() != null){
            CommonSet commonSet = commonSetMapper.selectByPrimaryKey(commonIdDTO.getCommonId());
            if(commonSet == null){
                result.setMessage("单词书不存在");
                return;
            }
        }
        if(type.equals(Consts.REVIEWING)){
            userMapper.updateReviewingBookByPrimaryKey(commonIdDTO);
        }else if(type.equals(Consts.ADDING)){
            userMapper.updateAddingBookByPrimaryKey(commonIdDTO);
        }
        result.setStatus(Consts.SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(PasswordUpdateDTO updateDTO, BaseResult result) {
        User user = userMapper.selectByPrimaryKey(updateDTO.getUserId());
        if(user == null){
            result.setMessage("用户不存在");
            return;
        }
        String clientOldPwd;
        String clientNewPwd;
        String serverPwd;
        String serverNewPassword;
        try{
            clientOldPwd = passwordUtils.decryptAESForClient(updateDTO.getOldPassword(),passwordUtils.getCLIENT_SKEY(),passwordUtils.getCLIENT_IVV());
            clientNewPwd = passwordUtils.decryptAESForClient(updateDTO.getNewPassword(),passwordUtils.getCLIENT_SKEY(),passwordUtils.getCLIENT_IVV());
            serverPwd = passwordUtils.decryptAES(user.getPassword(), passwordUtils.getSERVER_CRYPT_KEY(),passwordUtils.getSERVER_IV());
            serverNewPassword = passwordUtils.encryptAES(passwordUtils.s20()+clientNewPwd,passwordUtils.getSERVER_CRYPT_KEY(),passwordUtils.getSERVER_IV() );
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("客户端密码解密出现异常");
            return;
        }
        if(!clientOldPwd.equals(serverPwd)){
            result.setMessage("旧密码错误，请重新输入");
            return;
        }
        User updateUser = new User();
        updateUser.setId(updateDTO.getUserId());
        updateUser.setPassword(serverNewPassword);
        //updateUser.setUpdateTime(new Date());
        userMapper.updateByPrimaryKeySelective(updateUser);
        result.success();
    }
}
