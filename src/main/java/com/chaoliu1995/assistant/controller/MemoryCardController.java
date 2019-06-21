package com.chaoliu1995.assistant.controller;

import com.chaoliu1995.assistant.dto.*;
import com.chaoliu1995.assistant.entity.CommonSet;
import com.chaoliu1995.assistant.entity.MemoryCard;
import com.chaoliu1995.assistant.service.MemoryCardService;
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
 * @Date: 2019/4/15 16:03
 */
@Api( tags = "记忆卡片", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping("/memoryCard")
public class MemoryCardController extends BaseController {

    @Autowired
    private MemoryCardService memoryCardService;

    @ApiOperation(value="添加卡片")
    @PostMapping("/add")
    public BaseResult add(@RequestBody @Valid AddMemoryCardDTO addMemoryCardDTO, BindingResult bindingResult){
        BaseResult result = new BaseResult();
        if (bindingResult.hasErrors()){
            result.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return result;
        }
        addMemoryCardDTO.setUserId(getUserId());
        memoryCardService.add(addMemoryCardDTO,result);
        return result;
    }

    @ApiOperation(value="删除卡片")
    @PostMapping("/delete")
    public BaseResult delete(@RequestBody CommonIdDTO commonIdDTO){
        BaseResult result = new BaseResult();
        if(commonIdDTO.getCommonId() == null){
            result.setMessage("参数不完整");
            return result;
        }
        commonIdDTO.setUserId(getUserId());
        memoryCardService.delete(commonIdDTO,result);
        return result;
    }

    @ApiOperation(value="分页获取数据")
    @PostMapping("/list")
    public ResultsDTO<ListMemoryCardResultDTO> listByTags(@RequestBody @Valid ListMemoryCardDTO listDTO, BindingResult bindingResult){
        ResultsDTO<ListMemoryCardResultDTO> resultsDTO = new ResultsDTO<>();
        if (bindingResult.hasErrors()){
            resultsDTO.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return resultsDTO;
        }
        listDTO.setUserId(getUserId());
        memoryCardService.list(listDTO,resultsDTO);
        return resultsDTO;
    }

    @ApiOperation(value="添加卡包")
    @PostMapping("/cardBag/add")
    public BaseResult addCardBag(@RequestBody CommonAddDTO commonAddDTO){
        BaseResult result = new BaseResult();
        if(StringUtils.isEmpty(commonAddDTO.getCommonName())){
            result.setMessage("请填写卡包名称");
            return result;
        }
        commonAddDTO.setUserId(getUserId());
        memoryCardService.addCardBag(commonAddDTO,result);
        return result;
    }

    @ApiOperation(value="删除卡包")
    @PostMapping("/cardBag/delete")
    public BaseResult deleteCardBag(@RequestBody CommonIdDTO commonIdDTO){
        BaseResult result = new BaseResult();
        if(commonIdDTO.getCommonId() == null){
            result.setMessage("参数不完整");
            return result;
        }
        commonIdDTO.setUserId(getUserId());
        memoryCardService.deleteCardBag(commonIdDTO,result);
        return result;
    }

    @ApiOperation(value="分页获取卡包")
    @PostMapping("/cardBag/list")
    public ResultsDTO<CommonSet> listCardBag(@RequestBody @Valid CommonListDTO listDTO, BindingResult bindingResult){
        ResultsDTO<CommonSet> resultsDTO = new ResultsDTO<>();
        if (bindingResult.hasErrors()){
            resultsDTO.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return resultsDTO;
        }
        listDTO.setUserId(getUserId());
        memoryCardService.listCardBag(listDTO,resultsDTO);
        return resultsDTO;
    }

    @ApiOperation(value="随机抽取一张卡片")
    @PostMapping("/random")
    public ResultDTO<MemoryCard> randomCard(@RequestBody RandomCardDTO randomCardDTO){
        ResultDTO<MemoryCard> resultDTO = new ResultDTO<>();
        if(randomCardDTO.getCardBagId() == null){
            resultDTO.setMessage("请选择一个卡包");
            return resultDTO;
        }
        randomCardDTO.setUserId(getUserId());
        memoryCardService.randomCard(randomCardDTO,resultDTO);
        return resultDTO;
    }

    @ApiOperation(value="根据id返回卡片全部信息")
    @PostMapping("/get")
    public ResultDTO<MemoryCard> get(@RequestBody CommonIdDTO commonIdDTO){
        ResultDTO<MemoryCard> resultDTO = new ResultDTO<>();
        if(commonIdDTO.getCommonId() == null){
            resultDTO.setMessage("参数不完整");
            return resultDTO;
        }
        commonIdDTO.setUserId(getUserId());
        memoryCardService.get(commonIdDTO,resultDTO);
        return resultDTO;
    }

    @ApiOperation(value="修改卡片数据")
    @PostMapping("/update")
    public BaseResult update(@RequestBody @Valid UpdateMemoryCardDTO updateMemoryCardDTO, BindingResult bindingResult){
        BaseResult result = new BaseResult();
        if (bindingResult.hasErrors()){
            result.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return result;
        }
        updateMemoryCardDTO.setUserId(getUserId());
        memoryCardService.update(updateMemoryCardDTO,result);
        return result;
    }
}
