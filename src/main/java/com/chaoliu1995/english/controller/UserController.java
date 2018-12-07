package com.chaoliu1995.english.controller;

import com.chaoliu1995.english.base.BaseController;
import com.chaoliu1995.english.dao.UpdateCurrentBookDTO;
import com.chaoliu1995.english.dto.BaseResult;
import com.chaoliu1995.english.dto.ResultDTO;
import com.chaoliu1995.english.entity.Book;
import com.chaoliu1995.english.service.BookWordService;
import com.chaoliu1995.english.service.UserService;
import com.chaoliu1995.english.util.Consts;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/6 14:37
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookWordService bookWordService;

    @ApiOperation(value="修改正在复习的书籍", notes="")
    @RequestMapping(value="/reviewingBook/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResult updateReviewingBook(@RequestBody UpdateCurrentBookDTO updateDTO){
        BaseResult result = new BaseResult();
        if(updateDTO.getBookId() == null){
            result.setMessage("书籍id不能为空");
            return result;
        }
        updateDTO.setUserId(getUser().getId());
        userService.updateCurrentBook(updateDTO,Consts.REVIEWING,result);
        if (result.getStatus().equals(Consts.SUCCESS)){
            getUser().setReviewingBookId(updateDTO.getBookId());
        }
        return result;
    }

    @ApiOperation(value="修改正在添加单词的书籍", notes="")
    @RequestMapping(value="/addingBook/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResult updateAddingBook(@RequestBody UpdateCurrentBookDTO updateDTO){
        BaseResult result = new BaseResult();
        if(updateDTO.getBookId() == null){
            result.setMessage("书籍id不能为空");
            return result;
        }
        updateDTO.setUserId(getUser().getId());
        userService.updateCurrentBook(updateDTO,Consts.ADDING,result);
        if (result.getStatus().equals(Consts.SUCCESS)){
            getUser().setAddingBookId(updateDTO.getBookId());
        }
        return result;
    }

    @ApiOperation(value="获取当前用户正在复习的书籍", notes="")
    @RequestMapping(value="/reviewingBook", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO<Book> getReviewingBook(){
        ResultDTO<Book> resultDTO = new ResultDTO<>();
        Integer bookId = getUser().getReviewingBookId();
        if(bookId == null){
            resultDTO.setStatus(Consts.SUCCESS);
            return resultDTO;
        }
        Book book = bookWordService.getBookById(bookId);
        resultDTO.setData(book);
        resultDTO.setStatus(Consts.SUCCESS);
        return resultDTO;
    }

    @ApiOperation(value="获取当前用户正在添加单词的书籍", notes="")
    @RequestMapping(value="/addingBook", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO<Book> getAddingBook(){
        ResultDTO<Book> resultDTO = new ResultDTO<>();
        Integer bookId = getUser().getAddingBookId();
        if(bookId == null){
            resultDTO.setStatus(Consts.SUCCESS);
            return resultDTO;
        }
        Book book = bookWordService.getBookById(bookId);
        resultDTO.setData(book);
        resultDTO.setStatus(Consts.SUCCESS);
        return resultDTO;
    }

}
