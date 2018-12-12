package com.chaoliu1995.english.dao;

import com.chaoliu1995.english.entity.WechatUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/12 11:31
 */
@Component("wechatUserMapper")
public interface WechatUserMapper extends Mapper<WechatUser> {

    /**
     *
     * @param unionId
     * @return
     */
    WechatUser getByUnionId(@Param("unionId") String unionId);

}
