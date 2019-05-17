package com.chaoliu1995.assistant.util;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/11/21 17:56
 */
public class ArrayUtils {

    public static boolean contains(String[] strArray,String str){
        for(String temp : strArray){
            if(temp.equals(str)){
                return true;
            }
        }
        return false;
    }

}
