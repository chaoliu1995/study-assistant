package com.chaoliu1995.assistant.service.impl;


import com.chaoliu1995.assistant.dto.BaseResult;
import com.chaoliu1995.assistant.dto.CommonIdDTO;
import com.chaoliu1995.assistant.entity.CommonSet;
import com.chaoliu1995.assistant.mapper.CommonSetMapper;
import com.chaoliu1995.assistant.mapper.UserMapper;
import com.chaoliu1995.assistant.service.UserService;
import com.chaoliu1995.assistant.util.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
