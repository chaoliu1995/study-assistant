package com.chaoliu1995.assistant.controller;

import com.chaoliu1995.assistant.dto.CommonListDTO;
import com.chaoliu1995.assistant.dto.ResultDTO;
import com.chaoliu1995.assistant.dto.ResultsDTO;
import com.chaoliu1995.assistant.dto.SearchDTO;
import com.chaoliu1995.assistant.entity.UserWord;
import com.chaoliu1995.assistant.entity.shanbay.TabWord;
import com.chaoliu1995.assistant.mq.Producer;
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

@Api(description = "单词查询相关接口", basePath = "/word")
@RestController
@RequestMapping("/word")
public class SearchController extends BaseController {
	
	@Autowired
	private TabWordService tabWordService;

	@Autowired
	private Producer producer;

    /**
     * 查询单词
     * @param searchDTO
     * @return
     */
    @ApiOperation(value="查询单词", notes="")
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<TabWord> search(@RequestBody @Valid SearchDTO searchDTO, BindingResult bindingResult) {
		ResultDTO<TabWord> resultDTO = new ResultDTO<>();
		if (bindingResult.hasErrors()){
			resultDTO.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
			return resultDTO;
		}
		tabWordService.getWord(searchDTO.getWord(),resultDTO);
		if(resultDTO.getStatus().equals(Consts.SUCCESS)){
			producer.sendMessage(Consts.USER_WORD_QUEUE,StringUtils.getGson().toJson(new UserWord(getUser().getId(),resultDTO.getData().getId())));
			return resultDTO;
		}
        tabWordService.search(searchDTO.getWord(),resultDTO,getUser().getId());
		return resultDTO;
	}

    /**
     * 查询单词列表
     * @param searchListDTO
     * @return
     */
    @ApiOperation(value="分页获取单词列表", notes="")
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultsDTO<TabWord> listWord(@RequestBody CommonListDTO listDTO){
		ResultsDTO<TabWord> resultsDTO = new ResultsDTO<>();
        listDTO.setUserId(getUserId());
        tabWordService.listTabWord(resultsDTO,listDTO);
        return resultsDTO;
    }
}
