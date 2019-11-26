package com.chaoliu1995.assistant.controller;

import com.chaoliu1995.assistant.dto.*;
import com.chaoliu1995.assistant.entity.CommonSet;
import com.chaoliu1995.assistant.entity.shanbay.TabWord;
import com.chaoliu1995.assistant.service.BookWordService;
import com.chaoliu1995.assistant.service.TabWordService;
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
 * @Date: 2019/5/21 17:59
 */
@Api(tags = "单词相关接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping("/api/word")
public class WordController extends BaseController{

    @Autowired
    private TabWordService tabWordService;

    @Autowired
    private BookWordService bookWordService;

    @ApiOperation("查询单词")
    @PostMapping("/search")
    public ResultDTO<TabWord> search(@RequestBody @Valid SearchDTO searchDTO, BindingResult bindingResult) {
        ResultDTO<TabWord> resultDTO = new ResultDTO<>();
        if (bindingResult.hasErrors()){
            resultDTO.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return resultDTO;
        }
        searchDTO.setUserId(getUserId());
        tabWordService.getWord(searchDTO,resultDTO);
        if(!resultDTO.getStatus().equals(Consts.SUCCESS)){
            tabWordService.search(searchDTO,resultDTO);
        }
        return resultDTO;
    }

    @ApiOperation("分页获取单词列表")
    @PostMapping("/list")
    public ResultsDTO<TabWord> listWord(@RequestBody CommonListDTO listDTO){
        ResultsDTO<TabWord> resultsDTO = new ResultsDTO<>();
        listDTO.setUserId(getUserId());
        tabWordService.listTabWord(resultsDTO,listDTO);
        return resultsDTO;
    }

    @ApiOperation("随机获取一个待复习的单词")
    @RequestMapping(value="/random")
    public ResultDTO<WaitReviewDTO> random(@RequestBody RandomWordDTO randomWordDTO){
        ResultDTO<WaitReviewDTO> resultDTO = new ResultDTO<>();
        if(randomWordDTO.getWordId() != null){
            if(randomWordDTO.getStatus() == null){
                resultDTO.setMessage(Consts.PARAMETER_IS_NULL);
                return resultDTO;
            }
        }
        randomWordDTO.setUserId(getUserId());
        tabWordService.randomWord(randomWordDTO,resultDTO);
        return resultDTO;
    }

    @ApiOperation("添加单词本")
    @PostMapping("/wordBook/add")
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

    @ApiOperation("删除单词本")
    @PostMapping("/wordBook/delete")
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

    @ApiOperation("给单词本中添加单词")
    @PostMapping("/wordBook/addWord")
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

    @ApiOperation("分页获取单词本列表")
    @PostMapping("/wordBook/list")
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
