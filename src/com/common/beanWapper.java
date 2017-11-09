package com.common;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.PropertyValue;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by chen on 2017/3/26.
 */
public class beanWapper {
    String wapperName;

    public String getWapperName() {
        return wapperName;
    }

    public void setWapperName(String wapperName) {
        this.wapperName = wapperName;
    }
}

class beanWapperTest{
    public static void main(String[] args) {
        String [] sensitiveWords=new String[]{"s","k1"};
        String comment="啊啊啊啊s1k1k1ssssss";
//        if(comment!=null){
//            for (String sw : sensitiveWords) {
//                comment.replaceAll("s","d");
//            }
//        }
        comment=comment.replaceAll("s","f");
        System.out.println(comment);

//        beanWapper beanWapper=new beanWapper();
//        BeanWrapper bw= PropertyAccessorFactory.forBeanPropertyAccess(beanWapper);
//        bw.setPropertyValue("wapperName","哈哈哈");
//        System.out.println(beanWapper.getWapperName());
//        PropertyValue value=new PropertyValue("wapperName","嘻嘻嘻嘻");
//        bw.setPropertyValue(value);
//        System.out.println(beanWapper.getWapperName());
//
//        Scanner sc=new Scanner(System.in);
//        while (sc.hasNext()){
//            String s=sc.next();
////            if(s.equals("123")){
////                System.out.println(44444);
////            }
//            System.out.println(s);
//        }
//        AbstractHandlerMapping
//        RequestMappingHandlerMapping
//        RequestMappingHandlerAdapter

//        String target="";
//        for (int i = 0; i < 50000; i++) {
//            String s=Integer.valueOf(i).toString();
//            target=target+s;
//        }
//        System.out.println(target);

//        StringBuilder target1=new StringBuilder("");
//        for (int i = 0; i < 50000; i++) {
//            String s=Integer.valueOf(i).toString();
//            target1.append(s);
//        }
//        System.out.println(target1.toString());
    }
}
