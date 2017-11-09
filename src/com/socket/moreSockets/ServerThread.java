package com.socket.moreSockets;

import com.pojo.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by chen on 2017/11/4.
 */
public class ServerThread extends Thread {
    Socket socket = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }
    public void run(){
        try {

            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objIn = new ObjectInputStream(inputStream);
            System.out.println(socket.getInetAddress());
            System.out.println(socket.getInetAddress().getHostName());
            OutputStream out = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(out);
            User user = (User)objIn.readObject();
            System.out.println("用户信息为 用户名:"+user.getName()+"密码:"+user.getPwd());
            pw.flush();
            pw.close();
            out.close();
            objIn.close();
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
