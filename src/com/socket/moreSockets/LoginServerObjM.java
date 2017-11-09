package com.socket.moreSockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by chen on 2017/11/3.
 */
public class LoginServerObjM {


    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8800);
            Socket socket = null;
            int num = 0;
            while (true){
                socket = serverSocket.accept();
                ServerThread thread = new ServerThread(socket);
                thread.start();
                num++;
                System.out.println("访问数量"+num);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
