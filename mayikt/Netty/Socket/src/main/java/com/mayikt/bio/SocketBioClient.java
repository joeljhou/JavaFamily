package com.mayikt.bio;

import java.io.IOException;
import java.net.Socket;

/**
 * @author 周宇
 * @create 2021-09-19 9:19
 */
public class SocketBioClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9001);
        //向服务端发送数据
        socket.getOutputStream().write("来演示下同步阻塞Bio".getBytes());
        socket.getOutputStream().flush();
        System.out.println("向服务端发送数据结束");
        byte[] bytes = new byte[1024];
        //接收服务端回传的数据
        socket.getInputStream().read(bytes);
        System.out.println("接收到服务端的数据：" + new String(bytes).trim());
        socket.close();
    }
}
