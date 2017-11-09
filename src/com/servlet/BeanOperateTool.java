package com.servlet;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by chen on 2017/11/5.
 */
public class BeanOperateTool {

    public static Object setValue(Object obj, HttpServletRequest req){
        try {
            Class<?> cls = obj.getClass();
            Object object = cls.newInstance();
            Enumeration<String> enu = req.getParameterNames();
            Field[] fields=cls.getDeclaredFields();
            List<Field> fieldList = Arrays.asList(fields);
            List<String> fieldNameList = new ArrayList<>();
            for (Field f : fieldList) {
                fieldNameList.add(f.getName());
            }
            while (enu.hasMoreElements()){
                String paramName = enu.nextElement();
                if(!fieldNameList.contains(paramName)){
                    continue;
                }
                String paramValue = req.getParameter(paramName);
                System.out.println("paramName = "+paramName+"----paramValue = "+paramValue);
                Field field = cls.getDeclaredField(paramName);
                Method method = cls.getMethod("set"+UpperFirstLetter(paramName),field.getType());
                if("string".equalsIgnoreCase(field.getType().getSimpleName())){
                    method.invoke(object,paramValue);
                }else if("integer".equalsIgnoreCase(field.getType().getSimpleName())){
                    method.invoke(object, Integer.valueOf(paramValue));
                }else if("long".equalsIgnoreCase(field.getType().getSimpleName())){
                    method.invoke(object, Long.valueOf(paramValue));
                }else{
                    //暂时不解析其他数据类型
                    method.invoke(object,null);
                }
            }
            return object;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String UpperFirstLetter(String str){
        return str.substring(0,1).toUpperCase().concat(str.substring(1).toLowerCase());
    }
}
