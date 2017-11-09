package com.socket.moreSockets;

import com.pojo.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by chen on 2017/11/3.
 */
public class LoginClientObjM {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",8800);
            OutputStream out = socket.getOutputStream();
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            InputStream in = socket.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in));
            PrintWriter pw = new PrintWriter(out);
            User user = new User();
            user.setName("chen1");
            user.setPwd("1234");
            objOut.writeObject(user);

            socket.shutdownOutput();

            String back = null;
            while(!((back=rd.readLine())==null)){
                System.out.println(back);
            }
            pw.close();
            rd.close();
            in.close();
            objOut.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
