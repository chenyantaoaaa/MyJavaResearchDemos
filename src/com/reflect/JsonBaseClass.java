package com.reflect;

import net.sf.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * json对象转化为java对象基类（利用反射原理）
 *
 * @author yantao.chen
 * @date 2017/11/7.
 */
public class JsonBaseClass {

    /**
     *用于json对象转化为java对象
     * @param obj 子类对象
     * @param json 传入的json对象
     * @param PropertyList 需要进一步解析的属性名
     */
    public void setJsonProperties(Object obj, JSONObject json, List<String> PropertyList){
        if(PropertyList == null){
            PropertyList = new ArrayList<>();
        }
        try{
            Class<?> cls = obj.getClass();
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                String name = field.getName();
                if(!PropertyList.contains(name)) {
                    Method method = cls.getMethod("set" + UpperFirstLetter(name), field.getType());
                    String param = method.getParameterTypes()[0].getSimpleName().toLowerCase();
                    switch (param) {
                        case "string":
                            method.invoke(obj, json.optString(name));
                            break;
                        case "int":
                            method.invoke(obj, json.optInt(name));
                            break;
                        case "long":
                            method.invoke(obj, json.optLong(name));
                            break;
                        case "object":
                            method.invoke(obj, json.opt(name));
                            break;
                        case "boolean":
                            method.invoke(obj, json.optBoolean(name));
                            break;
                        case "double":
                            method.invoke(obj, json.optDouble(name));
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String UpperFirstLetter(String str){
        return str.substring(0,1).toUpperCase().concat(str.substring(1).toLowerCase());
    }

    public void setOtherClass(JSONObject json,List<String> propertyList){

        for (String propertyName : propertyList) {
            try {
                JSONObject data = json.optJSONObject(propertyName);
                Field field = this.getClass().getDeclaredField(propertyName);
                Method method = this.getClass().getMethod("set"+UpperFirstLetter(propertyName),field.getType());
                String thisName = this.getClass().getName();
                String className = thisName.substring(0,thisName.lastIndexOf("."))+"."+propertyName;

                Class<?> cls = Class.forName(className);
                //获取传入json的构造方法
                Constructor c1 = cls.getDeclaredConstructor(JSONObject.class,List.class);
                c1.setAccessible(true);
                Object ob = c1.newInstance(data,propertyList);
                method.invoke(this,ob);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

    }
}
