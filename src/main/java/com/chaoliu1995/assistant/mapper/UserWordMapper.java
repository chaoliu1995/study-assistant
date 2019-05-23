package com.chaoliu1995.assistant.mapper;

import com.chaoliu1995.assistant.dto.RandomWordDTO;
import com.chaoliu1995.assistant.entity.UserWord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/6 10:20
 */
@Component("userWordMapper")
public interface UserWordMapper extends Mapper<UserWord> {
    /**
     * 查询待复习单词总数
     * @return
     */
    int countWaitReview(@Param("userId") Integer userId);

    /**
     * 根据用户id，和当前时间，随机获取一个单词
     * @param userId
     * @return
     */
    Integer randomWordIdByCurrentTime(@Param("userId") Integer userId);

    /**
     * 根据用户id，随机获取一个单词
     * @param userId
     * @return
     */
    Integer randomWordId(@Param("userId") Integer userId);

    /**
     * 更新单词的下次出现时间
     * @param randomWordDTO
     */
    void updateShowTime(@Param("randomWordDTO") RandomWordDTO randomWordDTO);
}
