package com.servlet;

/**
 * @author yantao.chen
 * @date 2017/11/6.
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
//        MyClassLoader mcl = new MyClassLoader();
//        try {
//            Class<?> cls = mcl.loadFile("com.pojo.St");
//            Object obj = cls.newInstance();
//            System.out.println("obj=="+obj);
//
//            Class<?> cls2 = mcl.loadClass("com.pojo.St");
//            Object obj2 = cls2.newInstance();
//            System.out.println("obj2=="+obj2);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        System.out.println(System.getProperty("sun.boot.class.path"));
//        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
    }
}
