package com.thread.FileToDatabase;

import com.utils.ExcelUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by chenyantao
 * 2017/12/31.
 */
public class FIleTodata {
    private static int ThreadCount = 4;
    private static int FinishCount = 0;

    private static ArrayBlockingQueue queue = new ArrayBlockingQueue(1000);

    private static boolean isFinish = false;

    private static File sqlFile = new File("C:\\Users\\chen\\Desktop\\sqlFile.txt");
    private static File file = new File("C:\\Users\\chen\\Desktop\\testEx.txt");

    private static long count = 0;

    private static StringBuffer bf = new StringBuffer();

    private static AtomicLong atomicCount = new AtomicLong(0);

    public static void main(String[] args) {
        F2D();
    }

    public static void printCount(){
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(
                    new FileInputStream(sqlFile));
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            while ((line = br.readLine())!= null) {
                count++;
            }
            System.out.println(count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class WriteDataThread implements Runnable{
        @Override
        public void run() {
            try {
                if(!sqlFile.exists()){
                    sqlFile.createNewFile();
                }
                while (!(queue.size() == 0 && isFinish == true)) {
                    System.out.println(queue.size());
                    if (queue.size() > 0) {
                        atomicCount.incrementAndGet();
                        bf.append(queue.take() + System.getProperty("line.separator"));
                    }
                }
                FinishCount++;
                System.out.println("atomicCount ="+atomicCount.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void F2D(){
//        File file = new File("D:\\2000W\\最后5000.csv");
//        File file = new File("C:\\Users\\chen\\Desktop\\excel1.xlsx");

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(ThreadCount);
        for (int i = 0; i < ThreadCount; i++) {
            fixedThreadPool.execute(new WriteDataThread());
        }
//          new Thread() {
//             public void run() {
//
//             }
//          }.start();

        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(
                    new FileInputStream(file));
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            while ((line = br.readLine())!= null) {
                if(queue.size()<1000) {
                    queue.add(line);
                }else{
                    while(1==1){
                        if(queue.offer(line)){
                            break;
                        }
                    }
                }
            }
            isFinish = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (1==1) {
            if (FinishCount == ThreadCount) {
                try {
                    BufferedWriter out = new BufferedWriter(new FileWriter(sqlFile));
                    out.write(bf.toString());
                    out.flush(); // 把缓存区内容压入文件
                    out.close();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        printCount();

//        while (queue.size()>0){
//
//        }


//        System.out.println(list);
    }
}
