package com.chaoliu1995.english.mq;

import com.chaoliu1995.english.dao.UserWordMapper;
import com.chaoliu1995.english.entity.UserWord;
import com.chaoliu1995.english.util.Consts;
import com.chaoliu1995.english.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @Author: ChaoLiu
 * @Description: 消息消费者
 * @Date: 2018/12/6 10:53
 */
@Component
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserWordMapper userWordMapper;

    @JmsListener(destination = "userWordQueue.queue")
    public void receiveQueue(String text) {
        UserWord userWord = StringUtils.getGson().fromJson(text,UserWord.class);
        if(userWord == null){
            logger.error("解析UserWord出现问题");
        }
        UserWord dbUserWord = userWordMapper.selectOne(userWord);
        if(dbUserWord == null){
            userWord.setSearchCount(Consts.ONE.intValue());
            userWord.setShowTime(System.currentTimeMillis() / Consts.ONE_THOUSAND);
            userWordMapper.insert(userWord);
        }else{
            userWordMapper.updateSearchCountByPrimaryKey(dbUserWord.getId());
        }
    }
}
