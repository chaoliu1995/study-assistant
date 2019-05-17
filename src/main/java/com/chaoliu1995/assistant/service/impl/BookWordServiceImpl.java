package com.chaoliu1995.assistant.service.impl;

import com.chaoliu1995.assistant.dto.*;
import com.chaoliu1995.assistant.entity.BookWord;
import com.chaoliu1995.assistant.entity.CommonSet;
import com.chaoliu1995.assistant.entity.shanbay.TabWord;
import com.chaoliu1995.assistant.mapper.BookWordMapper;
import com.chaoliu1995.assistant.mapper.CommonSetMapper;
import com.chaoliu1995.assistant.mapper.TabWordMapper;
import com.chaoliu1995.assistant.service.BookWordService;
import com.chaoliu1995.assistant.util.Consts;
import com.chaoliu1995.assistant.util.EntityUtils;
import com.chaoliu1995.assistant.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/5 14:49
 */
@Service("bookWordService")
@Transactional(readOnly = true)
public class BookWordServiceImpl implements BookWordService {

    @Autowired
    private CommonSetMapper commonSetMapper;

    @Autowired
    private BookWordMapper bookWordMapper;

    @Autowired
    private TabWordMapper tabWordMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addWordBook(CommonAddDTO commonAddDTO, BaseResult result) {
        List<CommonSet> list = commonSetMapper.select(new CommonSet(Consts.WORD_BOOK,commonAddDTO.getUserId(),commonAddDTO.getCommonName()));
        if(list != null){
            result.setMessage("书籍名称已存在");
            return;
        }
        CommonSet commonSet = new CommonSet(Consts.WORD_BOOK,commonAddDTO.getUserId(),commonAddDTO.getCommonName());
        commonSetMapper.insert(commonSet);
        result.setStatus(Consts.SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addWord(InsertBookWordDTO insertBookWordDTO, BaseResult result) {
        CommonSet commonSet = commonSetMapper.selectByPrimaryKey(insertBookWordDTO.getBookId());
        if(commonSet == null){
            result.setMessage("单词书不存在");
            return;
        }
        if((commonSet.getUserId() - insertBookWordDTO.getUserId()) != 0){
            result.setMessage("当前用户不是此单词书的创建者，禁止添加单词");
            return;
        }
        TabWord tabWord = tabWordMapper.selectByPrimaryKey(insertBookWordDTO.getWordId());
        if(tabWord == null){
            result.setMessage("单词不存在");
            return;
        }
        BookWord bookWord = new BookWord();
        bookWord.setBookId(commonSet.getId());
        bookWord.setWordId(tabWord.getId());
        BookWord tempBookWord = bookWordMapper.selectOne(bookWord);
        if(tempBookWord != null){
            result.setMessage("单词已存在");
            return;
        }
        bookWordMapper.insert(bookWord);
        result.setStatus(Consts.SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void listWordBook(CommonListDTO listDTO, ResultsDTO<CommonSet> resultsDTO) {
        int total = commonSetMapper.countByCommonListDTO(listDTO,Consts.WORD_BOOK);
        Pager pager = new Pager(listDTO.getPage(),listDTO.getRows(),total);
        listDTO.setPage(pager.getCurrentPage());
        listDTO.setRows(pager.getPageSize());
        listDTO.setStart(pager.getStart());
        List<CommonSet> list = commonSetMapper.listByCommonListDTO(listDTO, Consts.WORD_BOOK);
        resultsDTO.setData(list);
        resultsDTO.setTotal(total);
        resultsDTO.setStatus(Consts.SUCCESS);
    }

    @Override
    public CommonSet getWordBookById(Integer wordBookId) {
        return commonSetMapper.selectByPrimaryKey(wordBookId);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteWordBook(CommonIdDTO commonIdDTO, BaseResult result) {
        CommonSet commonSet = commonSetMapper.selectByPrimaryKey(commonIdDTO.getCommonId());
        if(EntityUtils.identityConfirm(commonSet,commonIdDTO.getUserId(),result,"单词书")){
            return;
        }
        commonSetMapper.deleteByPrimaryKey(commonSet.getId());
        bookWordMapper.deleteByBookId(commonSet.getId());
        result.setStatus(Consts.SUCCESS);
    }
}
