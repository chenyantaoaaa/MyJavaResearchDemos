package com.socket.classLoader;

/**
 * Created by chen on 2017/11/5.
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
        System.out.println(new ClassLoaderDemo().getClass().getClassLoader());
    }
}
