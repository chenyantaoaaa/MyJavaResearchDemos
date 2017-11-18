package com.thread.Lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by chenyantao
 * 2017/11/18.
 */
public class RWlockTest {
    public static void main(String[] args) {
        //创建一个锁对象 ,非公平锁
        ReadWriteLock lock = new ReentrantReadWriteLock(false);
        //创建一个线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        //设置一个账号，设置初始金额为10000
        Account account = new Account(lock,"123456",10000);

        for (int i = 0; i < 1000; i++) {
            Operations oper1 = new Operations(account,"save",100);
            Operations oper2 = new Operations(account,"take",100);
            pool.execute(oper1);
            pool.execute(oper2);
        }
        pool.shutdown();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("账户最后余额"+account.getMoney());

    }
}
