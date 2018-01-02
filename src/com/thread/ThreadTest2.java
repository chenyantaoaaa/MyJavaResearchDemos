package com.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author chenyantao
 * @date 2017/12/19.
 */
public class ThreadTest2 {
    public static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(1000);

    private static AtomicLong atomic = new AtomicLong(0);
    private static AtomicLong atomic1 = new AtomicLong(0);

    public static void main(String[] args) {
        DataPut dataPut = new DataPut();
        dataPut.start();
//        for (int i = 0; i < 100000; i++) {
//            queue.add(i);
//        }
//        System.out.println(atomic);
        ExecutorService executorService = new ThreadPoolExecutor(5, 10, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
            for (int i = 0; i < 9; i++) {
                while (true) {
                    if(queue.size()>0) {
                        executorService.execute(new Runnable() {
                            @Override
                            public void run() {
                                while(true) {
                                    try {
                                        queue.take().toString();
                                        atomic.incrementAndGet();
                                        System.out.println("consumer---"+atomic);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    if(queue.size()==0){
//                                        System.out.println("final"+atomic);
                                    }
                                }
                            }
                        });
                    }
                }
            }
    }

    static class DataPut extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 3000; i++) {
                if(i!=atomic1.get()){
                    System.out.println("i="+i+"----atomic1="+atomic1+"----queueSize="+queue.size());
                }
                if(queue.size()<1000) {
                    queue.add(i);
                    atomic1.incrementAndGet();
                }else{
                    try {
                        Thread.sleep(30L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("producter---"+atomic1);
            }
        }
    }
}

