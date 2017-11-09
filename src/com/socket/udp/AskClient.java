package com.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by chen on 2017/11/4.
 */
public class AskClient {
    public static void main(String[] args) {

        try {
            String msg = "您好，我想咨询一个问题";
            byte [] buf = msg.getBytes();
            InetAddress ia  = null;
            try {
                ia = InetAddress.getByName("localhost");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            DatagramPacket dp = new DatagramPacket(buf,buf.length,ia,8900);
            DatagramSocket ds = new DatagramSocket();
            try {
                ds.send(dp);
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte [] buf2 = new byte[1024];
            DatagramPacket dp2 = new DatagramPacket(buf2,buf2.length);
            try {
                ds.receive(dp2);
                String returnMsg = new String(buf2,0,buf2.length);
                System.out.println(returnMsg);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ds.close();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
