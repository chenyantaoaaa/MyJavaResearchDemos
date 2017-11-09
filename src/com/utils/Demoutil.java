package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yantao.chen
 * @date 2017/5/16.
 */
public class Demoutil {
    public static  boolean demoJudge(int i){
        if(i==1) {
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(1/0);
        } catch (Exception e) {
            System.out.println("catch"+e);
        }
        System.out.println(123);
    }

    /*
* 将时间转换为时间戳
*/
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
}
