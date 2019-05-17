package com.chaoliu1995.assistant.service;

import com.chaoliu1995.assistant.dto.*;
import com.chaoliu1995.assistant.entity.CommonSet;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/5 14:48
 */
public interface BookWordService {

    /**
     * 添加单词书
     * @param commonAddDTO
     * @param result
     */
    void addWordBook(CommonAddDTO commonAddDTO, BaseResult result);

    /**
     * 删除单词书
     * @param commonIdDTO
     * @param result
     */
    void deleteWordBook(CommonIdDTO commonIdDTO, BaseResult result);

    /**
     * 添加书籍和单词的关联
     * @param insertBookWordDTO
     * @param result
     */
    void addWord(InsertBookWordDTO insertBookWordDTO, BaseResult result);

    /**
     * 分页获取单词书数据
     * @param listDTO
     * @param resultsDTO
     */
    void listWordBook(CommonListDTO listDTO, ResultsDTO<CommonSet> resultsDTO);

    /**
     * 根据id查询单词书信息
     * @param wordBookId
     * @return
     */
    CommonSet getWordBookById(Integer wordBookId);
}
