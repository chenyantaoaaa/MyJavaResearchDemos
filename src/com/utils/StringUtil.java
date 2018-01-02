package com.utils;

/**
 * @author chenyantao
 * @date 2017/11/10.
 */
public class StringUtil {
    public static String upperFirstLetter(String str){
        return str.substring(0,1).toUpperCase().concat(str.substring(1).toLowerCase());
    }

    public static boolean isEmpty(Object arg) {
        if (arg != null && !arg.equals("")&&!arg.equals("null")) {
            return false;
        }
        return true;
    }

    /**
     * 字符串反转 abc --> cba; a --> a
     */
    public static String reverse(String str) {
        if(str == null || str.length() == 1){
            return str;
        }
        char[] ch = str.toCharArray();
        int len = str.length();
        for(int i= 0; i< len/ 2; i++) {
            ch[i]^= ch[len- 1- i];
            ch[len- 1- i]^= ch[i];
            ch[i]^= ch[len- 1- i];
        }
        return new String(ch);
    }

    /**
     * Hbase生成rowKey专用
     * @return
     */
    public static String  getCurMillRes(){
        long cur = Long.MAX_VALUE - System.currentTimeMillis();
        return String.valueOf(cur);
    }
}
