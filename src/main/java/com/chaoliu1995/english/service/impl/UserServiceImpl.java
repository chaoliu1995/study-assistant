package com.chaoliu1995.english.service.impl;

import com.chaoliu1995.english.dao.BookMapper;
import com.chaoliu1995.english.dao.UpdateCurrentBookDTO;
import com.chaoliu1995.english.dao.UserMapper;
import com.chaoliu1995.english.dto.BaseResult;
import com.chaoliu1995.english.entity.Book;
import com.chaoliu1995.english.service.UserService;
import com.chaoliu1995.english.util.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/6 14:43
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void updateCurrentBook(UpdateCurrentBookDTO updateDTO, String type, BaseResult result) {
        if(updateDTO.getBookId() != null){
            Book book = bookMapper.selectByPrimaryKey(updateDTO.getBookId());
            if(book == null){
                result.setMessage("书籍不存在");
                return;
            }
        }
        if(type.equals(Consts.REVIEWING)){
            userMapper.updateReviewingBookByPrimaryKey(updateDTO.getUserId(),updateDTO.getBookId());
        }else if(type.equals(Consts.ADDING)){
            userMapper.updateAddingBookByPrimaryKey(updateDTO.getUserId(),updateDTO.getBookId());
        }
        result.setStatus(Consts.SUCCESS);
    }

}
