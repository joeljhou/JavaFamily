package com.mayikt.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 周宇
 * @create 2021-09-19 9:17
 */
public class SocketBioServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9001);
        while (true) {
            System.out.println("服务器端正在等待连接中...");
            // 阻塞方法 如果没有客户端与服务器端建立连接时，该方法会阻塞等待
            final Socket socket = serverSocket.accept();
            System.out.println("有客户端和我连接啦");
            //如果不使用异步线程处理接受io操作的情况下，有可能会阻塞等待 无法接受新的连接请求。
            new Thread(() -> {
                try {
                    handler(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            //handler(socket);
        }
    }

    private static void handler(Socket socket) throws IOException {
        System.out.println("线程id= " + Thread.currentThread().getId());
        byte[] bytes = new byte[1024];

        System.out.println("开始read。。");
        //接收客户端的数据，如果没有读取到客户端数据时，该方法也会阻塞
        int read = socket.getInputStream().read(bytes);
        System.out.println("read结束");
        if (read != -1) {
            System.out.println("接收到客户端的数据：" + new String(bytes, 0, read));
            System.out.println("线程id= = " + Thread.currentThread().getId());
        }
        socket.getOutputStream().write("效果演示完毕~~".getBytes());
        socket.getOutputStream().flush();
    }
}
