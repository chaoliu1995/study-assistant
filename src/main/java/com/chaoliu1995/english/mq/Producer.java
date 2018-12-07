package com.chaoliu1995.english.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: ChaoLiu
 * @Description: 消息生产者
 * @Date: 2018/12/6 10:52
 */
@Service("producer")
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    public void sendMessage(String queue, final Object message) {
        jmsTemplate.convertAndSend(queue, message);
    }

}
