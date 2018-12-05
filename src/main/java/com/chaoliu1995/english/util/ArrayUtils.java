package com.chaoliu1995.english.util;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/5 14:33
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
