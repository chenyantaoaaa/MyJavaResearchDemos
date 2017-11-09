package com.socket.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by chen on 2017/11/3.
 */
public class LoginServer {


    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8800);
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String info = null;
            OutputStream out = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(out);
            while(!((info=rd.readLine())==null)){
                System.out.println(info);
                String infos [] = info.split(",");
                String name = infos[0].substring(infos[0].indexOf(":")+1,infos[0].length());
                String pwd = infos[1].substring(infos[1].indexOf(":")+1,infos[1].length());
                if(name.equals("chen") && pwd.equals("123")){
                    pw.write("login success");
                }else{
                    pw.write("login fail");
                }
            }
            pw.flush();
            pw.close();
            out.close();
            rd.close();
            inputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
