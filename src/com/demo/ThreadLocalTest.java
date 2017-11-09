package com.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chen on 2017/10/22.
 */
public class ThreadLocalTest {
    static ThreadLocal<Integer> local = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    static Integer i1 = 0;

    static class Task implements Runnable{
        private int num;

        public Task(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            //获取当前线程的本地变量，然后累加10次
//            Integer i = local.get();
//            Integer i = i1;
//            while(++i<10);
//            System.out.println("Task " + num + "local num resutl is " + i);
//            Integer s=local.get()+1;
//            local.set(local.get()+1);
//            System.out.println(s);
            Integer s1=i1+1;
            i1=i1+1;
            System.out.println(s1);
        }
    }

    static void Test1(){
        System.out.println("main thread begin");
        ExecutorService executors = Executors.newCachedThreadPool();
        for(int i =1;i<=5;i++) {
            executors.execute(new Task(i));
        }
        executors.shutdown();
        System.out.println("main thread end");
    }

    public static void main(String[] args){
        Test1();
    }
}
