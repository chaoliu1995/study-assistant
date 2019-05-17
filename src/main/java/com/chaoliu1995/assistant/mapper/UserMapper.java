package com.chaoliu1995.assistant.mapper;

import com.chaoliu1995.assistant.dto.CommonIdDTO;
import com.chaoliu1995.assistant.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

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

    /**
     * 根据 username 或 email 查找用户信息
     * @param loginKey
     * @return
     */
    List<User> listUserByLoginKey(@Param("loginKey") String loginKey);
}
