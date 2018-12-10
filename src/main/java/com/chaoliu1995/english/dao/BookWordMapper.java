package com.chaoliu1995.english.dao;

import com.chaoliu1995.english.entity.BookWord;
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
     * 随机获取一个单词
     * @param bookIds
     * @return
     */
    Integer randomGetWaitReviewWordIdByBookIds(@Param("bookIds") String bookIds, @Param("userId") Integer userId);

    /**
     * 查询指定书中待复习单词的数量
     * @param bookIds
     * @return
     */
    int countWaitReviewByBookIds(@Param("bookIds") String bookIds, @Param("userId") Integer userId);

    /**
     * 根据 bookId 删除所有单词关联
     * @param bookId
     */
    void deleteByBookId(@Param("bookId") Integer bookId);
}
