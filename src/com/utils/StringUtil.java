package com.utils;

/**
 * @author chenyantao
 * @date 2017/11/10.
 */
public class StringUtil {
    public static String upperFirstLetter(String str){
        return str.substring(0,1).toUpperCase().concat(str.substring(1).toLowerCase());
    }
}
