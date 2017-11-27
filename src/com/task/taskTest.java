package com.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenyantao
 * 2017/11/23.
 */
@Component
public class taskTest {

    @Scheduled(cron="0/6 * *  * * ? ")   //每5秒执行一次
    public void myTest(){

    }

//    public static ScheduledExecutorService scheduExec = Executors.newScheduledThreadPool(3);
    public static ExecutorService executor= Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        OperateFile oper = new OperateFile("D:\\test\\test1.txt","A");
        oper.run();
//        scheduExec.scheduleWithFixedDelay(oper,0L,1L, TimeUnit.SECONDS);
//        executor.execute(oper);

        OperateFile oper2 = new OperateFile("D:\\test\\test1.txt","B");
        oper2.run();
//        executor.execute(oper2);

//        executor.shutdown();

    }
}
