package com.chaoliu1995.assistant.controller;

import com.chaoliu1995.assistant.dto.BaseResult;
import com.chaoliu1995.assistant.dto.CommonIdDTO;
import com.chaoliu1995.assistant.dto.ResultDTO;
import com.chaoliu1995.assistant.entity.CommonSet;
import com.chaoliu1995.assistant.service.BookWordService;
import com.chaoliu1995.assistant.service.UserService;
import com.chaoliu1995.assistant.util.Consts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/6 14:37
 */
@Api(tags = "用户相关", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookWordService bookWordService;

    @ApiOperation("修改正在复习的单词本")
    @PostMapping("/reviewingBook/update")
    public BaseResult updateReviewingBook(@RequestBody CommonIdDTO commonIdDTO){
        BaseResult result = new BaseResult();
        commonIdDTO.setUserId(getUser().getId());
        userService.updateCurrentBook(commonIdDTO,Consts.REVIEWING,result);
        if (result.getStatus().equals(Consts.SUCCESS)){
            getUser().setReviewingBookId(commonIdDTO.getCommonId());
        }
        return result;
    }

    @ApiOperation("修改正在添加单词的单词本")
    @PostMapping("/addingBook/update")
    public BaseResult updateAddingBook(@RequestBody CommonIdDTO commonIdDTO){
        BaseResult result = new BaseResult();
        commonIdDTO.setUserId(getUser().getId());
        userService.updateCurrentBook(commonIdDTO,Consts.ADDING,result);
        if (result.getStatus().equals(Consts.SUCCESS)){
            getUser().setAddingBookId(commonIdDTO.getCommonId());
        }
        return result;
    }

    @ApiOperation("获取当前用户正在复习的单词本")
    @PostMapping("/reviewingBook")
    public ResultDTO<CommonSet> getReviewingBook(){
        ResultDTO<CommonSet> resultDTO = new ResultDTO<>();
        Integer bookId = getUser().getReviewingBookId();
        if(bookId != null) {
            CommonSet commonSet = bookWordService.getWordBookById(bookId);
            resultDTO.setData(commonSet);
        }
        resultDTO.setStatus(Consts.SUCCESS);
        return resultDTO;
    }

    @ApiOperation("获取当前用户正在添加单词的单词本")
    @PostMapping("/addingBook")
    public ResultDTO<CommonSet> getAddingBook(){
        ResultDTO<CommonSet> resultDTO = new ResultDTO<>();
        Integer bookId = getUser().getAddingBookId();
        if(bookId != null){
            CommonSet commonSet = bookWordService.getWordBookById(bookId);
            resultDTO.setData(commonSet);
        }
        resultDTO.setStatus(Consts.SUCCESS);
        return resultDTO;
    }

}
