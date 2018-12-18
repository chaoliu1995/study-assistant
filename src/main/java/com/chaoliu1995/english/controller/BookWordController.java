package com.chaoliu1995.english.controller;

import com.chaoliu1995.english.base.BaseController;
import com.chaoliu1995.english.dto.*;
import com.chaoliu1995.english.entity.Book;
import com.chaoliu1995.english.service.BookWordService;
import com.chaoliu1995.english.util.Consts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/5 14:11
 */
@Api(description = "单词本相关接口", basePath = "/book")
@RestController
@RequestMapping("/book")
public class BookWordController extends BaseController {

    @Autowired
    private BookWordService bookWordService;

    @ApiOperation(value="添加单词本", notes="")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO<Book> add(@RequestBody @Valid AddBookDTO addBookDTO, BindingResult bindingResult){
        ResultDTO resultDTO = new ResultDTO();
        if (bindingResult.hasErrors()){
            resultDTO.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return resultDTO;
        }
        addBookDTO.setUserId(getUser().getId());
        bookWordService.addBook(addBookDTO,resultDTO);
        return resultDTO;
    }

    @ApiOperation(value="删除单词本", notes="")
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResult delete(@RequestBody @Valid DeleteBookDTO deleteBookDTO, BindingResult bindingResult){
        BaseResult result = new BaseResult();
        if (bindingResult.hasErrors()){
            result.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return result;
        }
        deleteBookDTO.setUserId(getUser().getId());
        bookWordService.deleteBook(deleteBookDTO,result);
        if(result.getStatus().equals(Consts.SUCCESS)){
            if(getUser().getAddingBookId() - deleteBookDTO.getBookId() == 0){
                getUser().setAddingBookId(null);
            }
            if(getUser().getReviewingBookId() - deleteBookDTO.getBookId() == 0){
                getUser().setReviewingBookId(null);
            }
        }
        return result;
    }

    @ApiOperation(value="给单词本中添加单词", notes="")
    @RequestMapping(value = "/word/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResult addWord(@RequestBody @Valid InsertBookWordDTO insertBookWordDTO, BindingResult bindingResult){
        BaseResult result = new BaseResult();
        if (bindingResult.hasErrors()){
            result.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return result;
        }
        insertBookWordDTO.setUserId(getUser().getId());
        bookWordService.addWord(insertBookWordDTO,result);
        return result;
    }

    @ApiOperation(value="分页获取单词本列表", notes="")
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultsDTO<Book> listBook(@RequestBody ListBookDTO listDTO){
        ResultsDTO<Book> resultsDTO = new ResultsDTO<>();
        listDTO.setUserId(getUser().getId());
        bookWordService.listBook(listDTO,resultsDTO);
        return resultsDTO;
    }
}
