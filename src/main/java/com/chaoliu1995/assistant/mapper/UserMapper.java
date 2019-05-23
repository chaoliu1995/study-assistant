package com.chaoliu1995.assistant.mapper;

import com.chaoliu1995.assistant.dto.CommonIdDTO;
import com.chaoliu1995.assistant.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Email: chaoliu1995@qq.com
 * @CreateDate: 2017年10月21日 下午7:47:29
 */
@Component("userMapper")
public interface UserMapper extends Mapper<User> {

    void updateReviewingBookByPrimaryKey(@Param("commonIdDTO")CommonIdDTO commonIdDTO);

    void updateAddingBookByPrimaryKey(@Param("commonIdDTO")CommonIdDTO commonIdDTO);
}
