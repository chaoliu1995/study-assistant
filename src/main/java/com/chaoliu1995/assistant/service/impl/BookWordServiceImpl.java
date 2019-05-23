package com.chaoliu1995.assistant.service.impl;

import com.chaoliu1995.assistant.dto.*;
import com.chaoliu1995.assistant.entity.BookWord;
import com.chaoliu1995.assistant.entity.CommonSet;
import com.chaoliu1995.assistant.entity.UserBook;
import com.chaoliu1995.assistant.entity.UserWord;
import com.chaoliu1995.assistant.entity.shanbay.TabWord;
import com.chaoliu1995.assistant.mapper.*;
import com.chaoliu1995.assistant.service.BookWordService;
import com.chaoliu1995.assistant.util.Consts;
import com.chaoliu1995.assistant.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    @Autowired
    private UserBookMapper userBookMapper;

    @Autowired
    private UserWordMapper userWordMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addWordBook(CommonAddDTO commonAddDTO, BaseResult result) {
        List<CommonSet> list = commonSetMapper.select(new CommonSet(Consts.WORD_BOOK,commonAddDTO.getUserId(),commonAddDTO.getCommonName()));
        if(list != null && list.size() > 0){
            result.setMessage("单词本已存在");
            return;
        }
        CommonSet commonSet = new CommonSet(Consts.WORD_BOOK,commonAddDTO.getUserId(),commonAddDTO.getCommonName());
        commonSet.setCreateTime(new Date());
        commonSetMapper.insert(commonSet);
        UserBook userBook = new UserBook(commonAddDTO.getUserId(),commonSet.getId());
        userBookMapper.insert(userBook);
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
        BookWord bookWord = new BookWord(commonSet.getId(),tabWord.getId());
        BookWord tempBookWord = bookWordMapper.selectOne(bookWord);
        if(tempBookWord != null){
            result.setMessage("单词已存在");
            return;
        }
        bookWordMapper.insert(bookWord);
        UserWord userWord = new UserWord(insertBookWordDTO.getUserId(),insertBookWordDTO.getWordId());
        UserWord tempUserWord = userWordMapper.selectOne(userWord);
        if(tempUserWord == null){
            userWord.init();
            userWordMapper.insert(userWord);
        }
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
        UserBook userBook = userBookMapper.selectOne(new UserBook(commonIdDTO.getUserId(),commonIdDTO.getCommonId()));
        if(userBook == null){
            result.setMessage("单词本不存在");
            return;
        }
        CommonSet commonSet = commonSetMapper.selectByPrimaryKey(commonIdDTO.getCommonId());
        if(commonSet.getUserId() - commonIdDTO.getUserId() == 0){
            List<UserBook> userBooks = userBookMapper.select(new UserBook(commonIdDTO.getCommonId()));
            if(userBooks.size() - Consts.ONE == 0){
                commonSetMapper.deleteByPrimaryKey(commonSet.getId());
                bookWordMapper.delete(new BookWord(commonIdDTO.getCommonId()));
            }
        }
        userBookMapper.deleteByPrimaryKey(userBook);
        result.setStatus(Consts.SUCCESS);
    }
}
