package com.zhangbin;

import com.zhangbin.client.SocketClient;
import com.zhangbin.service.SocketServer;

public class MyTest {


    public static void main(String[] args) {
//        SocketServer socketServer = new SocketServer();
//        socketServer.initSocketServer();
        SocketClient socketClient = new SocketClient();
        socketClient.initSocketClient();
        socketClient.sendFile();
    }



}
