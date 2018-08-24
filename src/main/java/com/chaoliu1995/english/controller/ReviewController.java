package com.chaoliu1995.english.controller;

import com.chaoliu1995.english.base.BaseController;
import com.chaoliu1995.english.dto.ResultDTO;
import com.chaoliu1995.english.dto.WaitReviewDTO;
import com.chaoliu1995.english.dto.WordMemoryDTO;
import com.chaoliu1995.english.entity.shanbay.TabWord;
import com.chaoliu1995.english.service.TabWordService;
import com.chaoliu1995.english.util.Consts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	public ResultDTO<WaitReviewDTO> getWord(){
		ResultDTO<WaitReviewDTO> resultDTO = new ResultDTO<WaitReviewDTO>();
		resultDTO.setStatus(Consts.ERROR);
		tabWordService.getWaitReviewWord(resultDTO);
		return resultDTO;
	}

    /**
     * 修改单词的熟悉程度
     * @param wordMemoryDTO
     * @return
     */
    @ApiOperation(value="判断单词的熟悉程度，以决定其下次出现的时间", notes="")
	@RequestMapping(value = "/memory", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<Object> memory(@RequestBody WordMemoryDTO wordMemoryDTO){
		ResultDTO<Object> resultDTO = ResultDTO.init();
		if(wordMemoryDTO == null || wordMemoryDTO.getWordId() == null || wordMemoryDTO.getMemoryStatus() == null){
			resultDTO.setMessage(Consts.PARAMETER_IS_NULL);
			return resultDTO;
		}
        if(wordMemoryDTO.getMemoryStatus() > 365 || wordMemoryDTO.getMemoryStatus() < 1){
            resultDTO.setMessage(Consts.PARAMETER_IS_NULL);
            return resultDTO;
        }
        wordMemoryDTO.setNextShowTime(System.currentTimeMillis() / 1000 + (86400 * wordMemoryDTO.getMemoryStatus()));
		tabWordService.memory(wordMemoryDTO);
		resultDTO.setStatus(Consts.SUCCESS);
		return resultDTO;
	}
}
