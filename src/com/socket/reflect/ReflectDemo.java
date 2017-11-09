package com.socket.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by chen on 2017/11/4.
 */
public class ReflectDemo {
    public static void main(String[] args) {
        try {
            Class<?> cls = Class.forName("com.socket.reflect.Student");
            Constructor cs = cls.getConstructor(String.class);
            Object obj = cs.newInstance("tom");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

class Student{
    private String name;

    public Student(String name) {
        this.name = name;
        System.out.println("student构造  name = "+name);
    }
}
