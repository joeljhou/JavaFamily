package com.mayikt.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 周宇
 * @create 2021-09-19 9:36
 * 模拟伪装nio底层原理
 */
public class SimulationNioTcpServer {

    /**
     * 保存SocketChannel
     */
    private static List<SocketChannel> listSocketChannel = new ArrayList<>();
    /**
     * 缓冲区大小
     */
    private static ByteBuffer byteBuffer = ByteBuffer.allocate(512);

    public static void main(String[] args) {
        try {
            // 1.创建一个ServerSocketChannel
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 2. 绑定地址
            ServerSocketChannel bind = serverSocketChannel.bind(new InetSocketAddress(8080));
            // 3.设置非阻塞模式
            serverSocketChannel.configureBlocking(false);
            while (true) {
                // 4.等待建立连接  如果设置非阻塞的情况下，如果没有获取连接的情况下直接返回null，如果建立连接之后返回socketChannel
                // 建立三次握手s
                SocketChannel socketChannel = serverSocketChannel.accept();
                // 5. 如果socketChannel 不为空的情况下，则将该连接保存起来。
                if (socketChannel != null) {
                    // 设置该socketChannel通道为fasle
                    socketChannel.configureBlocking(false);
                    listSocketChannel.add(socketChannel);
                }
                // 循环SocketChannel,检查每个SocketChannel中数据有传输数据
                for (SocketChannel scl : listSocketChannel) {
                    try {
                        // 6.以缓冲区方式读取
                        int read = scl.read(byteBuffer);
                        if (read > 0) {
                            byteBuffer.flip();
                            // 转换格式为中文的格式
                            Charset charset = Charset.forName("UTF-8");
                            String receiveText = charset.newDecoder().decode
                                    (byteBuffer.asReadOnlyBuffer()).toString();
                            System.out.println("receiveText:" + receiveText);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

