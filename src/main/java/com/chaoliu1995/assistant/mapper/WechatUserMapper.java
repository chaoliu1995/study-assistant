package com.chaoliu1995.assistant.mapper;

import com.chaoliu1995.assistant.entity.WechatUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/11/21 16:06
 */
@Component("wechatUserMapper")
public interface WechatUserMapper extends Mapper<WechatUser> {

    /**
     *
     * @param unionId
     * @return
     */
    WechatUser getByUnionId(@Param("unionId") String unionId);

    /**
     *
     * @param openId
     * @return
     */
    WechatUser getByOpenId(@Param("openId") String openId);
}
