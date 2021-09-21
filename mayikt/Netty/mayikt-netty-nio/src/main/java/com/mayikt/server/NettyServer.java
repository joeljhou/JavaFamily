package com.mayikt.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author 周宇
 * @create 2021-09-21 21:44
 */
public class NettyServer {
    private static int inetPort = 8080;

    public static void main(String[] args) {
        //使用Netty创建我们的服务器端的时候 采用两个线程池
        //boss线程池 专门接受我们的请求
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        //工作线程池  处理我们的请求读写操作
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        //创建我们的serverBootstrap
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //NioServerSocketChannel 标记当前使用为服务器端
        serverBootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        //处理每个请求的hanlder
                        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                        channel.pipeline().addLast(new StringEncoder());
                        //对发送数据设置边界 \r\n
                        channel.pipeline().addLast(new ServerHandler());
                    }
                });

        try {
            //绑定我们的端口号
            ChannelFuture channelFuture = serverBootstrap.bind(inetPort).sync();
            System.out.println("服务器端启动成功:" + inetPort);
            //等待监听我们的请求
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //优雅的关闭我们的线程池
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
