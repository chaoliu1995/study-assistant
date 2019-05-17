package com.chaoliu1995.assistant.util;

import java.util.Map;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/7/4 12:04
 */
public class MapUtils {

    private MapUtils(){}

    public static boolean isNotEmpty(Map map){
        if(map != null && map.keySet() != null && map.keySet().size() > 0){
            return true;
        }
        return false;
    }

}
