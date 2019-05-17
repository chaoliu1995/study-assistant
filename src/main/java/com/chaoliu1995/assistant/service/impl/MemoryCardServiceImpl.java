package com.chaoliu1995.assistant.service.impl;

import com.chaoliu1995.assistant.dto.*;
import com.chaoliu1995.assistant.entity.CommonSet;
import com.chaoliu1995.assistant.entity.MemoryCard;
import com.chaoliu1995.assistant.mapper.CommonSetMapper;
import com.chaoliu1995.assistant.mapper.MemoryCardMapper;
import com.chaoliu1995.assistant.service.MemoryCardService;
import com.chaoliu1995.assistant.util.Consts;
import com.chaoliu1995.assistant.util.EntityUtils;
import com.chaoliu1995.assistant.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2019/4/15 16:13
 */
@Service("memoryCardService")
@Transactional(readOnly = true)
public class MemoryCardServiceImpl implements MemoryCardService {

    @Autowired
    private MemoryCardMapper memoryCardMapper;

    @Autowired
    private CommonSetMapper commonSetMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(AddMemoryCardDTO addMemoryCardDTO, BaseResult result) {
        CommonSet commonSet = commonSetMapper.selectByPrimaryKey(addMemoryCardDTO.getCardBagId());
        if(EntityUtils.identityConfirm(commonSet,addMemoryCardDTO.getUserId(),result,"卡包")){
            return;
        }
        MemoryCard memoryCard = new MemoryCard();
        memoryCard.setQuestion(addMemoryCardDTO.getQuestion());
        memoryCard.setAnswer(addMemoryCardDTO.getAnswer());
        memoryCard.setCardBagId(addMemoryCardDTO.getCardBagId());
        memoryCard.setMemoryStatus(Consts.MEMORY_STATUS_1);
        Date date = new Date();
        memoryCard.setShowTime(date);
        memoryCard.setCreateTime(date);
        memoryCardMapper.insert(memoryCard);
        result.setStatus(Consts.SUCCESS);
    }

    @Override
    public void list(ListMemoryCardDTO listDTO, ResultsDTO<ListMemoryCardResultDTO> resultsDTO) {
        CommonSet commonSet = commonSetMapper.selectByPrimaryKey(listDTO.getCardBagId());
        if(commonSet == null){
            resultsDTO.setStatus(Consts.SUCCESS);
            return;
        }
        if(EntityUtils.identityConfirm(commonSet,listDTO.getUserId(),resultsDTO,"卡包")){
            return;
        }
        List<ListMemoryCardResultDTO> list;
        int total = memoryCardMapper.countByListDTO(listDTO);
        Pager pager = new Pager(listDTO.getPage(),listDTO.getRows(),total);
        listDTO.setRows(pager.getPageSize());
        listDTO.setStart(pager.getStart());
        list = memoryCardMapper.listByListDTO(listDTO);
        resultsDTO.setTotal(total);
        resultsDTO.setData(list);
        resultsDTO.setStatus(Consts.SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCardBag(CommonAddDTO commonAddDTO, BaseResult result) {
        CommonSet commonSet = new CommonSet();
        commonSet.setName(commonAddDTO.getCommonName());
        commonSet.setTypeKey(Consts.MEMORY_CARD);
        commonSet.setUserId(commonAddDTO.getUserId());
        commonSet.setCreateTime(new Date());
        commonSetMapper.insert(commonSet);
        result.setStatus(Consts.SUCCESS);
    }

    @Override
    public void listCardBag(CommonListDTO listDTO, ResultsDTO<CommonSet> resultsDTO) {
        List<CommonSet> list;
        int total = commonSetMapper.countByCommonListDTO(listDTO,Consts.MEMORY_CARD);
        Pager pager = new Pager(listDTO.getPage(),listDTO.getRows(),total);
        listDTO.setRows(pager.getPageSize());
        listDTO.setStart(pager.getStart());
        list = commonSetMapper.listByCommonListDTO(listDTO,Consts.MEMORY_CARD);
        resultsDTO.setData(list);
        resultsDTO.setTotal(total);
        resultsDTO.setStatus(Consts.SUCCESS);
    }

    @Override
    public void randomCard(RandomCardDTO randomCardDTO, ResultDTO<MemoryCard> resultDTO) {
        CommonSet commonSet = commonSetMapper.selectByPrimaryKey(randomCardDTO.getCardBagId());
        if(EntityUtils.identityConfirm(commonSet,randomCardDTO.getUserId(),resultDTO,"卡包")){
            return;
        }
        if(randomCardDTO.getMemoryCardId() != null){
            MemoryCard memoryCard = memoryCardMapper.selectByPrimaryKey(randomCardDTO.getMemoryCardId());
            if(memoryCard == null){
                resultDTO.setMessage("卡片不存在");
                return;
            }
            if(memoryCard.getCardBagId() - randomCardDTO.getCardBagId() != 0){
                resultDTO.setMessage("此卡片不属于当前卡包");
                return;
            }

            if(randomCardDTO.getStatus() - Consts.MEMORY_STATUS_10 > 0){
                randomCardDTO.setIntervalType(Consts.DAY);
                randomCardDTO.setInterval(365);
                randomCardDTO.setStatus(Consts.MEMORY_STATUS_10);
                memoryCardMapper.updateShowTime(randomCardDTO);
            }else{
                int memoryStatus = 0;
                if(randomCardDTO.getStatus() - Consts.MEMORY_STATUS_10 == 0){
                    memoryStatus = memoryCard.getMemoryStatus();
                    randomCardDTO.setStatus(memoryCard.getMemoryStatus() + Consts.ONE);
                }else if(randomCardDTO.getStatus() - Consts.MEMORY_STATUS_10 < 0){
                    memoryStatus = randomCardDTO.getStatus();
                    randomCardDTO.setStatus(randomCardDTO.getStatus() + Consts.ONE);
                }
                switch (memoryStatus){
                    case Consts.MEMORY_STATUS_1:
                        randomCardDTO.setIntervalType(Consts.MINUTE);
                        randomCardDTO.setInterval(5);
                        break;
                    case Consts.MEMORY_STATUS_2:
                        randomCardDTO.setIntervalType(Consts.MINUTE);
                        randomCardDTO.setInterval(30);
                        break;
                    case Consts.MEMORY_STATUS_3:
                        randomCardDTO.setIntervalType(Consts.HOUR);
                        randomCardDTO.setInterval(12);
                        break;
                    case Consts.MEMORY_STATUS_4:
                        randomCardDTO.setIntervalType(Consts.DAY);
                        randomCardDTO.setInterval(1);
                        break;
                    case Consts.MEMORY_STATUS_5:
                        randomCardDTO.setIntervalType(Consts.DAY);
                        randomCardDTO.setInterval(2);
                        break;
                    case Consts.MEMORY_STATUS_6:
                        randomCardDTO.setIntervalType(Consts.DAY);
                        randomCardDTO.setInterval(4);
                        break;
                    case Consts.MEMORY_STATUS_7:
                        randomCardDTO.setIntervalType(Consts.DAY);
                        randomCardDTO.setInterval(7);
                        break;
                    case Consts.MEMORY_STATUS_8:
                        randomCardDTO.setIntervalType(Consts.DAY);
                        randomCardDTO.setInterval(15);
                        break;
                    case Consts.MEMORY_STATUS_9:
                        randomCardDTO.setIntervalType(Consts.DAY);
                        randomCardDTO.setInterval(30);
                        break;
                }
                memoryCardMapper.updateShowTime(randomCardDTO);
            }
        }
        MemoryCard resultMemoryCard = memoryCardMapper.randomCardByCurrentTime(randomCardDTO);
        if(resultMemoryCard == null){
            resultMemoryCard = memoryCardMapper.randomCard(randomCardDTO);
        }
        resultDTO.setData(resultMemoryCard);
        resultDTO.setStatus(Consts.SUCCESS);
    }

    @Override
    public void get(CommonIdDTO commonIdDTO, ResultDTO<MemoryCard> resultDTO) {
        MemoryCard memoryCard = memoryCardMapper.selectByPrimaryKey(commonIdDTO.getCommonId());
        CommonSet commonSet = commonSetMapper.selectByPrimaryKey(memoryCard.getCardBagId());
        if(EntityUtils.identityConfirm(commonSet,commonIdDTO.getUserId(),resultDTO,"卡片")){
            return;
        }
        resultDTO.setData(memoryCard);
        resultDTO.setStatus(Consts.SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UpdateMemoryCardDTO updateMemoryCardDTO, BaseResult result) {
        MemoryCard memoryCard = memoryCardMapper.selectByPrimaryKey(updateMemoryCardDTO.getId());
        CommonSet commonSet = commonSetMapper.selectByPrimaryKey(memoryCard.getCardBagId());
        if(EntityUtils.identityConfirm(commonSet,updateMemoryCardDTO.getUserId(),result,"卡片")){
            return;
        }
        memoryCard.setQuestion(updateMemoryCardDTO.getQuestion());
        memoryCard.setAnswer(updateMemoryCardDTO.getAnswer());
        memoryCardMapper.updateByPrimaryKey(memoryCard);
        result.setStatus(Consts.SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCardBag(CommonIdDTO commonIdDTO, BaseResult result) {
        CommonSet commonSet = commonSetMapper.selectByPrimaryKey(commonIdDTO.getCommonId());
        if(EntityUtils.identityConfirm(commonSet,commonIdDTO.getUserId(),result,"卡包")){
            return;
        }
        commonSetMapper.deleteByPrimaryKey(commonIdDTO.getCommonId());
        MemoryCard memoryCard = new MemoryCard();
        memoryCard.setCardBagId(commonIdDTO.getCommonId());
        memoryCardMapper.delete(memoryCard);
        result.setStatus(Consts.SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(CommonIdDTO commonIdDTO, BaseResult result) {
        MemoryCard memoryCard = memoryCardMapper.selectByPrimaryKey(commonIdDTO.getCommonId());
        if(memoryCard == null){
            result.setMessage("卡片不存在");
            return;
        }
        CommonSet commonSet = commonSetMapper.selectByPrimaryKey(memoryCard.getCardBagId());
        if(commonSet == null){
            result.setMessage("卡包不存在");
            return;
        }
        if(EntityUtils.identityConfirm(commonSet,commonIdDTO.getUserId(),result,"卡片")){
            return;
        }
        memoryCardMapper.deleteByPrimaryKey(commonIdDTO.getCommonId());
        result.setStatus(Consts.SUCCESS);
    }
}
