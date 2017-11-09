package com.socket.socket;

import com.pojo.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by chen on 2017/11/3.
 */
public class LoginServerObj {


    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8800);
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objIn = new ObjectInputStream(inputStream);

            OutputStream out = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(out);
            User user = (User)objIn.readObject();
            System.out.println("用户信息为 用户名:"+user.getName()+"密码:"+user.getPwd());
//            while(!((info=rd.readLine())==null)){
//                System.out.println(info);
//                String infos [] = info.split(",");
//                String name = infos[0].substring(infos[0].indexOf(":")+1,infos[0].length());
//                String pwd = infos[1].substring(infos[1].indexOf(":")+1,infos[1].length());
//                if(name.equals("chen") && pwd.equals("123")){
//                    pw.write("login success");
//                }else{
//                    pw.write("login fail");
//                }
//            }
            pw.flush();
            pw.close();
            out.close();
            objIn.close();
            inputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
