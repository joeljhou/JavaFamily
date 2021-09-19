package com.mayikt.tcp.thread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 周宇
 * @create 2021-09-19 6:39
 */
public class SocketTcpBioServer {
    static byte[] bytes = new byte[1024];

    public static void main(String[] args) throws IOException {
        try {
            //1.创建ServerSocket
            final ServerSocket serverSocket = new ServerSocket();
            //2.绑定端口号
            serverSocket.bind(new InetSocketAddress(8080));
            System.out.println("开始等待接受数据...");
            final Socket accept = serverSocket.accept();

            while (true) {
                new Thread(() -> {
                    try {
                        int read = accept.getInputStream().read(bytes);
                        String result = new String(bytes).trim();
                        System.out.println("服务器端获取数据：" + result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
            //使用多线程技术实现伪异步io
            //缺点：消耗cpu服务器资源
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
