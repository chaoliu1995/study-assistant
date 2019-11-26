package com.chaoliu1995.assistant.controller;

import com.chaoliu1995.assistant.dto.*;
import com.chaoliu1995.assistant.entity.CommonSet;
import com.chaoliu1995.assistant.service.BookWordService;
import com.chaoliu1995.assistant.util.Consts;
import com.chaoliu1995.assistant.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/5 14:11
 */
@Api(tags = "单词本相关接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping("/api/wordBook")
public class BookWordController extends BaseController {

    @Autowired
    private BookWordService bookWordService;

    @ApiOperation(value="添加单词本")
    @PostMapping(value = "/add")
    public BaseResult add(@RequestBody CommonAddDTO commonAddDTO){
        BaseResult result = new BaseResult();
        if(StringUtils.isEmpty(commonAddDTO.getCommonName())){
            result.setMessage(Consts.PARAMETER_IS_NULL);
            return result;
        }
        commonAddDTO.setUserId(getUserId());
        bookWordService.addWordBook(commonAddDTO,result);
        return result;
    }

    @ApiOperation(value="删除单词本")
    @PostMapping(value = "/delete")
    public BaseResult delete(@RequestBody CommonIdDTO commonIdDTO){
        BaseResult result = new BaseResult();
        if (commonIdDTO.getCommonId() == null){
            result.setMessage(Consts.PARAMETER_IS_NULL);
            return result;
        }
        commonIdDTO.setUserId(getUserId());
        bookWordService.deleteWordBook(commonIdDTO,result);
        if(result.getStatus().equals(Consts.SUCCESS)){
            if(getUser().getAddingBookId() - commonIdDTO.getCommonId() == 0){
                getUser().setAddingBookId(null);
            }
            if(getUser().getReviewingBookId() - commonIdDTO.getCommonId() == 0){
                getUser().setReviewingBookId(null);
            }
        }
        return result;
    }

    @ApiOperation(value="给单词本中添加单词")
    @PostMapping(value = "/word/add")
    public BaseResult addWord(@RequestBody @Valid InsertBookWordDTO insertBookWordDTO, BindingResult bindingResult){
        BaseResult result = new BaseResult();
        if (bindingResult.hasErrors()){
            result.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return result;
        }
        insertBookWordDTO.setUserId(getUserId());
        bookWordService.addWord(insertBookWordDTO,result);
        return result;
    }

    @ApiOperation(value="分页获取单词本列表")
    @PostMapping(value = "/list")
    public ResultsDTO<CommonSet> listBook(@RequestBody @Valid CommonListDTO listDTO, BindingResult bindingResult){
        ResultsDTO<CommonSet> resultsDTO = new ResultsDTO<>();
        if (bindingResult.hasErrors()){
            resultsDTO.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return resultsDTO;
        }
        listDTO.setUserId(getUserId());
        bookWordService.listWordBook(listDTO,resultsDTO);
        return resultsDTO;
    }
}
