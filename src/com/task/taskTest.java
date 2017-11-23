package com.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by chenyantao
 * 2017/11/23.
 */
@Component
public class taskTest {

    @Scheduled(cron="0/6 * *  * * ? ")   //每5秒执行一次
    public void myTest(){

    }
}
