package com.chaoliu1995.assistant.mapper;

import com.chaoliu1995.assistant.dto.ListMemoryCardDTO;
import com.chaoliu1995.assistant.dto.ListMemoryCardResultDTO;
import com.chaoliu1995.assistant.dto.RandomCardDTO;
import com.chaoliu1995.assistant.entity.MemoryCard;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2019/4/15 16:13
 */
@Component("memoryCardMapper")
public interface MemoryCardMapper extends Mapper<MemoryCard> {
    /**
     *
     * @param listDTO
     * @return
     */
    int countByListDTO(@Param("listDTO") ListMemoryCardDTO listDTO);

    /**
     *
     * @param listDTO
     * @return
     */
    List<ListMemoryCardResultDTO> listByListDTO(@Param("listDTO")ListMemoryCardDTO listDTO);

    /**
     * 随机抽取一张卡，指定卡包id，并限制 show_time 小于当前时间
     * @param randomCardDTO
     * @return
     */
    MemoryCard randomCardByCurrentTime(@Param("randomCardDTO") RandomCardDTO randomCardDTO);

    /**
     * 随机抽取一张卡，指定卡包id
     * @param randomCardDTO
     * @return
     */
    MemoryCard randomCard(@Param("randomCardDTO") RandomCardDTO randomCardDTO);

    /**
     * 更新卡片的下次出现时间
     * @param randomCardDTO
     */
    void updateShowTime(@Param("randomCardDTO") RandomCardDTO randomCardDTO);
}
