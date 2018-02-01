package com.zhangbin.client;

import sun.awt.image.ByteInterleavedRaster;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClient {

    public Socket socket;


    public void initSocketClient() {
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 5776);
        socket = new Socket();
        try {
            socket.connect(socketAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFile() {
        String filePath = "E:\\workSpace_intellijIDEA\\hello.sh";
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        OutputStream os = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
            os = socket.getOutputStream();
            bos = new BufferedOutputStream(os);
            byte[] fileBytes = file2ByteArray(filePath);
            bos.write(fileBytes);
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                socket.shutdownOutput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private byte[] file2ByteArray(String filePath) throws IOException {

        InputStream in = new FileInputStream(filePath);
        byte[] data = toByteArray(in);
        in.close();

        return data;
    }

    private byte[] toByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }


}
