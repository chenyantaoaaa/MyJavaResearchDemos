package com.pojo;

import java.io.Serializable;

/**
 * Created by chen on 2017/11/3.
 */
public class User implements Serializable {
    private String name;

    private String pwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
