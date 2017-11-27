package com.task;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by chenyantao
 * 2017/11/24.
 */
public class OperateFile implements Runnable{
    private String fileName;

    private String threadName;

    private int num;

    public OperateFile(String fileName, String threadName) {
        this.fileName = fileName;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        try {
            FileWriter fw = new FileWriter(fileName,true);
            StringBuffer sb=new StringBuffer();
            for (int i = 0; i < 9900; i++) {
                sb.append("thread" + threadName + "------"+i+"\r\n");
            }
            fw.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void delFile(File file){
        if (!file.exists()) {
            return;
        } else {
            if (file.isFile())
                file.delete();
        }
    }
}
