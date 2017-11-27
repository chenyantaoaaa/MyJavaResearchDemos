package com.common;

import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.PropertyValues;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenyantao
 * @date 2017/11/27.
 */
public class beanWapperTes {

    private String name;

    private String age;

    @Test
    public void tt(){
        Map<String,String> map = new HashMap<>();
        map.put("name","chen");
        map.put("age","12");
        PropertyValues p = new MutablePropertyValues(map);
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(this);
        bw.setPropertyValues(p, true);
        System.out.println(this.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
