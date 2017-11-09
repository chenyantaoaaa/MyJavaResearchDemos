package com.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by chen on 2017/4/8.
 */
@Component
public class demoService {

    @Value("${test}")
    private String testConfig;

    public String testConfig(){
        return  testConfig;
    }
}
