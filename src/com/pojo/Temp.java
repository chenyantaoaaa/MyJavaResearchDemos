package com.pojo;

/**
 * Created by chen on 2017/11/5.
 */
public class Temp {
    private String name;

    public Temp() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Temp{" +
                "name='" + name + '\'' +
                '}';
    }
}
