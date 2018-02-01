package com.zhangbin.service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    private ServerSocket serverSocket;


    public void initSocketServer() {
        try {
            serverSocket = new ServerSocket(5776);
            receiveFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveFile() {
        String savePath = "E:\\workSpace_intellijIDEA\\tempRoot\\hello_copy.sh";
        byte[] buf = new byte[512];
        InputStream inputStream = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            File saveFile = new File(savePath);
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }
            Socket socketCli = serverSocket.accept();
            inputStream = socketCli.getInputStream();
            int len = 0;
            while ((len = inputStream.read(buf, 0, buf.length)) != -1) {
                bos= new BufferedOutputStream(new FileOutputStream(saveFile));
                bos.write(buf);
                bos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
