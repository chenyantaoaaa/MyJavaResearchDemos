package com.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenyantao
 * @date 2017/12/19.
 */
public class ThreadBasic {
    private static AtomicInteger num = new AtomicInteger();

    public static void main(String[] args) {
        num.set(0);
        for (int i = 0; i < 10; i++) {
            MyThread th = new MyThread();
            th.start();
        }
        System.out.println(num);
    }

    static class MyThread extends Thread{
        @Override
        public void run(){
            for (int i = 0; i < 1000; i++) {
                num.incrementAndGet();
                System.out.println(num);
            }
        }
    }
}
