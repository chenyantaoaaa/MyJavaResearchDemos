package com.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by chenyantao
 * 2017/11/23.
 */
@Component
public class MyConstants extends Properties{

    public static MyConstants P = null;

    public static String testValue = "";

    public static void init() throws IOException {
        if (P == null) {
            P = new MyConstants();
            String[] arrProFiles = { "redis.properties"};

            for (String proFile : arrProFiles) {
                MyConstants.P.load(MyConstants.class.getClassLoader().getResourceAsStream("resource/"+proFile));
            }
        }
    }

    /**
     * 载入所有配置文件
     */
    static {
        try {
            init();
            testValue = get("server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取配置文件中内容
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        String value = "";
        try {
            if (key == null || P == null) {
                return "";
            }
            if (P.containsKey(key)) {
                value = new String(P.getProperty(key).getBytes("UTF-8"), "UTF-8");
            } else {
                value = "";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }
}
