package com.mayikt.tcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;

/**
 * @author 周宇
 * @create 2021-09-17 2:00
 */
public class SocketTcpServer {
    public static void main(String[] args) throws IOException {
        // 创建Server Socket
        ServerSocket serverSocket = new ServerSocket();
        // 创建我们的 Socket 监听连接地址和端口号
        SocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(), 8080);
        // 绑定我们的监听地址
        serverSocket.bind(address);
        // 等待接受请求
        System.out.println("等待客户端发送消息..");
        Socket accept = serverSocket.accept();
        // 获取OutputStream流
        PrintWriter socketOut = new PrintWriter(accept.getOutputStream());
        byte buf[] = new byte[1024];
        if (accept.getInputStream().read(buf) > 0) {
            System.out.println("服务器端接受到客户端消息：" + new String(buf));
        }
        // 服务器端响应消息
        String sendStr = "每特教育平均就业薪资突破了3万";
        socketOut.write(sendStr);
        socketOut.flush();

        // 关闭所有连接
        socketOut.close();
        accept.close();
        serverSocket.close();
    }
}
