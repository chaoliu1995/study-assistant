package com.chaoliu1995.assistant.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/7/4 14:02
 */
@Component("projectConfig")
public class Config {

    @Getter
    @Setter
    @Value("${audioPath}")
    private String fileAudioPath;

}
