package com.mayikt.udp.client;

import java.io.IOException;
import java.net.*;

/**
 * @author 周宇
 * @create 2021-09-17 2:06
 */
public class SocketUdpClient {
    public static void main(String[] args) throws IOException {
        /*
         * 向服务器端发送数据
         */
        //1.定义服务器的地址、端口号、数据
        InetAddress address = InetAddress.getByName("localhost");
        int port = 8800;
        byte[] data = "每特教育第六期开班了吗？".getBytes();
        //2.创建数据报，包含发送的数据信息
        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
        //3.创建DatagramSocket对象
        DatagramSocket socket = new DatagramSocket();
        //4.向服务器端发送数据报
        socket.send(packet);

        /*
         * 接收服务器端响应的数据
         */
        //1.创建数据报，用于接收服务器端响应的数据
        byte[] data2 = new byte[1024];
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
        //2.接收服务器响应的数据
        socket.receive(packet2);
        //3.读取数据
        String reply = new String(data2, 0, packet2.getLength());
        System.out.println("我是客户端，服务器说：" + reply);
        //4.关闭资源
        socket.close();
    }
}
