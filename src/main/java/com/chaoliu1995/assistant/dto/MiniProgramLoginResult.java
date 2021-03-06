package com.chaoliu1995.assistant.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/11/21 15:23
 */
@Data
@NoArgsConstructor
public class MiniProgramLoginResult {
    /**
     * 用户唯一标识
     */
    private String openid;
    /**
     * 会话密钥
     */
    private String session_key;
    /**
     * 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
     * https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/union-id.html
     */
    private String unionid;
    /**
     * 错误码
     * -1 	    系统繁忙，此时请开发者稍候再试
     * 0 	    请求成功
     * 40029 	code 无效
     * 45011 	频率限制，每个用户每分钟100次
     */
    private Integer errcode;
    /**
     * 错误信息
     */
    private String errmsg;
}
