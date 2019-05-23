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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2019/5/21 17:59
 */
@Api(description = "单词相关接口", basePath = "/word")
@RestController
@RequestMapping("/word")
public class WordController extends BaseController{

    @Autowired
    private TabWordService tabWordService;

    @Autowired
    private BookWordService bookWordService;

    @ApiOperation(value="查询单词")
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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

    @ApiOperation(value="分页获取单词列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultsDTO<TabWord> listWord(@RequestBody CommonListDTO listDTO){
        ResultsDTO<TabWord> resultsDTO = new ResultsDTO<>();
        listDTO.setUserId(getUserId());
        tabWordService.listTabWord(resultsDTO,listDTO);
        return resultsDTO;
    }

    @ApiOperation(value="随机获取一个待复习的单词")
    @RequestMapping(value="/random", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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

    @ApiOperation(value="添加单词本")
    @RequestMapping(value = "/wordBook/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
    @RequestMapping(value = "/wordBook/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
    @RequestMapping(value = "/wordBook/addWord", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
    @RequestMapping(value = "/wordBook/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
