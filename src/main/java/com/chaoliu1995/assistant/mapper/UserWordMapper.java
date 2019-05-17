package com.chaoliu1995.assistant.mapper;

import com.chaoliu1995.assistant.dto.WordMemoryDTO;
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
     * 根据主键修改 searchCount 自增 1
     * @param userWordId
     */
    void updateSearchCountByPrimaryKey(@Param("userWordId") Integer userWordId);

    /**
     * 随机获取一个出现时间小于当前时间的单词
     * @return
     */
    Integer getWordIdByShowTime(@Param("userId") Integer userId);

    /**
     * 查询待复习单词总数
     * @return
     */
    int countForWaitReview(@Param("userId") Integer userId);

    /**
     * 记忆一个单词
     * @param wordMemoryDTO
     * @return
     */
    int memory(@Param("wordMemoryDTO") WordMemoryDTO wordMemoryDTO);
}
