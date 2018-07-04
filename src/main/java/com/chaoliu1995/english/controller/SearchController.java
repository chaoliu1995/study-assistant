package com.chaoliu1995.english.controller;

import com.chaoliu1995.english.base.BaseController;
import com.chaoliu1995.english.dto.ResultDTO;
import com.chaoliu1995.english.dto.SearchDTO;
import com.chaoliu1995.english.dto.SearchListDTO;
import com.chaoliu1995.english.entity.shanbay.TabWord;
import com.chaoliu1995.english.service.TabWordService;
import com.chaoliu1995.english.util.Consts;
import com.chaoliu1995.english.util.Pager;
import com.chaoliu1995.english.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/word")
public class SearchController extends BaseController {
	
	@Autowired
	private TabWordService tabWordService;

    /**
     * 查询单词
     * @param searchDTO
     * @return
     */
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<TabWord> search(@RequestBody SearchDTO searchDTO) {
		ResultDTO<TabWord> resultDTO = new ResultDTO<TabWord>();
		resultDTO.setStatus(Consts.ERROR);
		if(searchDTO != null && StringUtils.isEmpty(searchDTO.getWord())){
			resultDTO.setMessage(Consts.PARAMETER_IS_NULL);
			return resultDTO;
		}
        tabWordService.search(searchDTO.getWord(),resultDTO);
		return resultDTO;
	}

    /**
     * 查询单词列表
     * @param searchListDTO
     * @return
     */
    @RequestMapping(value="/list",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO<List<TabWord>> listWord(@RequestBody SearchListDTO searchListDTO){
	    ResultDTO<List<TabWord>> resultDTO = new ResultDTO<List<TabWord>>();
	    if(searchListDTO == null){
	        searchListDTO = new SearchListDTO();
        }
        Pager<TabWord> pager = tabWordService.listTabWordForPager(searchListDTO);
        resultDTO.setData(pager.getRecordList());
        resultDTO.setStatus(Consts.SUCCESS);
        return resultDTO;
    }
}
