package com.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;

/**
 * Created by chen on 2017/11/4.
 */
public class AskServer {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket(8900);
            byte [] buf = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buf,buf.length);
            ds.receive(dp);
            String msg = new String(buf,0,buf.length);
            System.out.println(msg);

            String back = "请问您有什么问题？";
            byte [] backBuf = back.getBytes();
            SocketAddress sa = dp.getSocketAddress();
            DatagramPacket dpBack = new DatagramPacket(backBuf,backBuf.length,sa);
            ds.send(dpBack);
            ds.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
