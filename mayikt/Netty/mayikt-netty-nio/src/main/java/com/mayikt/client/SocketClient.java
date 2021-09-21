package com.mayikt.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author 周宇
 * @create 2021-09-21 22:12
 */
public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8080);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("请问每特教育第六期平均薪资突破多少".getBytes());
        while (true){

        }
        //outputStream.close();
        //socket.close();
    }
}
