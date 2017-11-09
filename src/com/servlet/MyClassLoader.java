package com.servlet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author yantao.chen
 * @date 2017/11/6.
 */
public class MyClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("MyClassLoader loadClass");
        return super.loadClass(name);
    }

    public Class<?> loadFile(String ClassName) throws ClassNotFoundException {
        byte data [] = loadFileClass(ClassName);
        return super.defineClass(ClassName,data,0,data.length);
    }

    public byte [] loadFileClass(String ClassName) throws ClassNotFoundException {
        try {
            String result [] = ClassName.split("\\.");
            String name = result[result.length-1];
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte data [] = new byte[1024];
            File file = new File("D:\\test"+ File.separator+name+".class");
            FileInputStream input = new FileInputStream(file);
            int len = 0;
            while ((len = input.read(data))!=-1){
                out.write(data,0,len);
            }
            input.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
