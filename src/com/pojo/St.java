package com.pojo;

/**
 * @author yantao.chen
 * @date 2017/11/6.
 */
public class St {
    private String name;

    private String age;

    public St() {
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

    @Override
    public String toString() {
        return "St{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
