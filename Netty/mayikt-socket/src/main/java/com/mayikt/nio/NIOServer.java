package com.mayikt.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @author 周宇
 * @create 2021-09-19 10:24
 * java原生api nio实现
 */
public class NIOServer {
    /**
     * 创建一个选择器
     */
    private Selector selector;

    public void initServer(int port) throws IOException {
        //获取一个ServerSocketChannel通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置通道非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //将该通道对应的ServerSocket绑定到port端口
        serverSocketChannel.bind(new InetSocketAddress(port));
        //获得一个通道管理器
        this.selector = Selector.open();
        //将通道管理器和该通道绑定，并未该通道注册SelectionKey.OP_ACCEPT事件，注册该事件后，
        //当该事件到达时，selector.select() 会返回，如果该事件没到达selector.select()会一直阻塞
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void listen() throws IOException {
        System.out.println("服务端启动成功！");
        //轮询访问selector
        while (true) {
            //当注册的事件到达时，方法返回；否则，该方法一直阻塞
            int select = selector.select(10);
            if (select == 0) {
                continue;
            }
            //消息类型有很多种，发送消息连接

            //获得selector中选中的项的迭代器，选中的项为注册的事件
            Iterator<SelectionKey> ite = this.selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = ite.next();
                //删除已选的key，以防重复处理
                ite.remove();

                //发送一个消息 蚂蚁课堂牛逼
                if (key.isAcceptable()) { //客户端请求连接事件
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    //获得客户端连接的通道
                    SocketChannel channel = server.accept();
                    //设置成非阻塞
                    channel.configureBlocking(false);

                    //在和客户端连接成功之后，为了可以连接到客户端的信息，需要给通道设置读的权限
                    channel.register(this.selector, SelectionKey.OP_READ);

                } else if (key.isReadable()) {
                    read(key);
                }
            }
        }
    }

    private void read(SelectionKey key) throws IOException {
        //服务器可读取消息：得到事件发生的Socket通道
        SocketChannel channel = (SocketChannel) key.channel();
        //创建读取的缓存区
        ByteBuffer buffer = ByteBuffer.allocate(512);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        System.out.println("服务器端收到消息：" + msg);
        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8));
        channel.write(outBuffer);  //将消息回发给客户端
    }

    public static void main(String[] args) throws IOException {
        NIOServer server = new NIOServer();
        server.initServer(8080);
        server.listen();
    }
}
