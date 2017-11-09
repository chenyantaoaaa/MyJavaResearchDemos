package com.socket.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by chen on 2017/11/3.
 */
public class LoginClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",8800);
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in));
            PrintWriter pw = new PrintWriter(out);
            String info = "用户名:chen,用户密码:123";
            pw.write(info);

            pw.flush();
            socket.shutdownOutput();

            String back = null;
            while(!((back=rd.readLine())==null)){
                System.out.println(back);
            }
            pw.close();
            rd.close();
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
