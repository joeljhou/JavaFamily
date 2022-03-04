package com.mayikt.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author 周宇
 * @create 2021-09-17 2:01
 */
public class SocketTcpClient {
    public static void main(String[] args) throws IOException {
        // 创建socket
        final Socket socket = new Socket();
        // 创建socket地址
        SocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(), 8080);
        socket.connect(address);
        // 创建PrintWriter
        PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
        BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // 向服务器发送的内容
        String sendStr = "客户端问服务器端：你们每特教育第六期平均就业薪资突破多少？";
        socketOut.write(sendStr);
        socketOut.flush();
        String receiveStr = socketIn.readLine();
        System.out.println("服务器端回复：: " + receiveStr);

        // 关闭连接
        socketOut.close();
        socketIn.close();
        socket.close();
    }
}
