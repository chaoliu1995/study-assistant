package com.chaoliu1995.english.controller;

import com.chaoliu1995.english.base.BaseController;
import com.chaoliu1995.english.dto.*;
import com.chaoliu1995.english.service.TabWordService;
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
import java.util.TimeZone;

/** 
* @Author: LiuChao
* @Description: 单词复习相关操作
* @Email: chaoliu1995@QQ.com
* @CreateDate: 2017年10月17日 下午1:28:38
*/
@Api(description = "复习单词相关接口", basePath = "/review")
@RestController
@RequestMapping("/review")
public class ReviewController extends BaseController {
	
	@Autowired
	private TabWordService tabWordService;

    @ApiOperation(value="随机获取一个待复习的单词", notes="")
	@RequestMapping(value="/getWord", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<WaitReviewDTO> getWord(@RequestBody ReviewWordDTO reviewWordDTO){
		ResultDTO<WaitReviewDTO> resultDTO = new ResultDTO<>();
		reviewWordDTO.setUserId(getUser().getId());
		reviewWordDTO.setBookId(getUser().getReviewingBookId());
		tabWordService.getWaitReviewWord(reviewWordDTO,resultDTO);
		return resultDTO;
	}

    /**
     * 修改单词的熟悉程度
     * @param wordMemoryDTO
     * @return
     */
    @ApiOperation(value="判断单词的熟悉程度，以决定其下次出现的时间", notes="")
	@RequestMapping(value = "/memory", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public BaseResult memory(@RequestBody @Valid WordMemoryDTO wordMemoryDTO, BindingResult bindingResult){
		BaseResult result = new BaseResult();
		if (bindingResult.hasErrors()){
			result.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
			return result;
		}
        wordMemoryDTO.setNextShowTime(computeTimeNillis(wordMemoryDTO.getMemoryStatus()));
		wordMemoryDTO.setUserId(getUser().getId());
        tabWordService.memory(wordMemoryDTO);
		result.setStatus(Consts.SUCCESS);
		return result;
	}

	/**
	 * 计算下次显示时间
	 * @param num
	 * @return
	 */
	private long computeTimeNillis(int num){
		return System.currentTimeMillis() / Consts.ONE_THOUSAND / 86400 * 86400 - TimeZone.getDefault().getRawOffset() + (86400 * num);
	}
}
