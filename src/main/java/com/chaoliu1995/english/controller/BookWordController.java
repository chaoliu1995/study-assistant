package com.chaoliu1995.english.controller;

import com.chaoliu1995.english.base.BaseController;
import com.chaoliu1995.english.dto.*;
import com.chaoliu1995.english.entity.Book;
import com.chaoliu1995.english.service.BookWordService;
import com.chaoliu1995.english.util.StringUtils;
import io.swagger.annotations.Api;
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
 * @Date: 2018/12/5 14:11
 */
@Api(description = "单词书相关接口", basePath = "/book")
@RestController
@RequestMapping("/book")
public class BookWordController extends BaseController {

    @Autowired
    private BookWordService bookWordService;

    @ApiOperation(value="添加单词书", notes="")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO<Book> add(@RequestBody String name){
        ResultDTO resultDTO = new ResultDTO();
        if(StringUtils.isEmpty(name)){
            resultDTO.setMessage("书籍名称不可以为空");
            return resultDTO;
        }
        bookWordService.addBook(name,getUser().getId(),resultDTO);
        return resultDTO;
    }

    @ApiOperation(value="给书中添加单词", notes="")
    @RequestMapping(value = "/word/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResult addWord(@RequestBody InsertBookWordDTO insertBookWordDTO){
        BaseResult result = new BaseResult();
        bookWordService.addWord(insertBookWordDTO,getUser().getId(),result);
        return result;
    }

    @ApiOperation(value="分页获取书籍列表", notes="")
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultsDTO<Book> listBook(@RequestBody ListBookDTO listDTO){
        ResultsDTO<Book> resultsDTO = new ResultsDTO<>();
        listDTO.setUserId(getUser().getId());
        bookWordService.listBook(listDTO,resultsDTO);
        return resultsDTO;
    }
}
