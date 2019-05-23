package com.chaoliu1995.assistant.mapper;

import com.chaoliu1995.assistant.entity.BookWord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/5 14:29
 */
@Component("bookWordMapper")
public interface BookWordMapper extends Mapper<BookWord> {

    /**
     * 根据用户id和单词本id、当前时间，随机获取一个单词id
     * @param userId
     * @param bookId
     * @return
     */
    Integer randomWordIdByCurrentTime(@Param("userId")Integer userId, @Param("bookId")Integer bookId);

    /**
     * 根据用户id和单词本id，随机获取一个单词id
     * @param userId
     * @param bookId
     * @return
     */
    Integer randomWordId(@Param("userId")Integer userId, @Param("bookId")Integer bookId);

    /**
     *
     * @param userId
     * @param bookId
     * @return
     */
    int countWaitReview(@Param("userId")Integer userId, @Param("bookId")Integer bookId);

}
