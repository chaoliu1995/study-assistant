package com.chaoliu1995.assistant.service;

import com.chaoliu1995.assistant.dto.*;
import com.chaoliu1995.assistant.entity.CommonSet;
import com.chaoliu1995.assistant.entity.MemoryCard;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2019/4/15 16:13
 */
public interface MemoryCardService {
    /**
     * 添加
     * @param addMemoryCardDTO
     * @param result
     */
    void add(AddMemoryCardDTO addMemoryCardDTO, BaseResult result);

    /**
     * 分页获取数据
     * @param listDTO
     * @param resultsDTO
     * @return
     */
    void list(ListMemoryCardDTO listDTO, ResultsDTO<ListMemoryCardResultDTO> resultsDTO);

    /**
     * 添加卡包
     * @param commonAddDTO
     * @param result
     */
    void addCardBag(CommonAddDTO commonAddDTO,BaseResult result);

    /**
     * 分页获取卡包数据
     * @param listDTO
     * @param resultsDTO
     */
    void listCardBag(CommonListDTO listDTO, ResultsDTO<CommonSet> resultsDTO);

    /**
     * 随机抽取一张卡
     * @param randomCardDTO
     * @param resultDTO
     */
    void randomCard(RandomCardDTO randomCardDTO,ResultDTO<MemoryCard> resultDTO);

    /**
     * 根据id，返回卡片全部信息
     * @param commonIdDTO
     * @param resultDTO
     */
    void get(CommonIdDTO commonIdDTO,ResultDTO<MemoryCard> resultDTO);

    /**
     * 修改卡片信息
     * @param updateMemoryCardDTO
     * @param result
     */
    void update(UpdateMemoryCardDTO updateMemoryCardDTO,BaseResult result);

    /**
     * 删除卡包
     * @param commonIdDTO
     * @param result
     */
    void deleteCardBag(CommonIdDTO commonIdDTO,BaseResult result);

    /**
     * 删除卡片
     * @param commonIdDTO
     * @param result
     */
    void delete(CommonIdDTO commonIdDTO,BaseResult result);
}
