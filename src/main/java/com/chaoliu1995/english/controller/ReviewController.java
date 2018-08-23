package com.chaoliu1995.english.controller;

import com.chaoliu1995.english.base.BaseController;
import com.chaoliu1995.english.dto.ResultDTO;
import com.chaoliu1995.english.dto.WordMemoryDTO;
import com.chaoliu1995.english.entity.shanbay.TabWord;
import com.chaoliu1995.english.service.TabWordService;
import com.chaoliu1995.english.util.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
* @Author: LiuChao
* @Description: 单词复习相关操作
* @Email: chaoliu1995@QQ.com
* @CreateDate: 2017年10月17日 下午1:28:38
*/
@RestController
@RequestMapping("/review")
public class ReviewController extends BaseController {
	
	@Autowired
	private TabWordService tabWordService;

    /**
     * 随机获取一个陌生的单词
     * @return
     */
	@RequestMapping(value="/getWord", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<TabWord> getWord(){
		ResultDTO<TabWord> resultDTO = new ResultDTO<TabWord>();
		resultDTO.setStatus(Consts.ERROR);
		TabWord word = tabWordService.getTabWordByOperateTotalOrderEsc();
		if(word == null){
			resultDTO.setMessage("程序异常");
			return resultDTO;
		}
		resultDTO.setData(word);
		resultDTO.setStatus(Consts.SUCCESS);
		return resultDTO;
	}

    /**
     * 修改单词的熟悉程度
     * @param wordMemoryDTO
     * @return
     */
	@RequestMapping("/memory")
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
