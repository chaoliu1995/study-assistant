package com.chaoliu1995.assistant.config;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2019/5/14 11:35
 */
@Component("memoryStorage")
public class MemoryStorage {
    private Map<String,Integer> loginCodeMap = new HashMap<>();

    public Integer getUserId(String loginCode){
        return loginCodeMap.get(loginCode);
    }

    public void put(String loginCode, Integer userId){
        this.loginCodeMap.put(loginCode,userId);
    }

    public void remove(String loginCode){
        this.loginCodeMap.remove(loginCode);
    }

    public boolean containsKey(String loginCode){
        return this.loginCodeMap.containsKey(loginCode);
    }
}
