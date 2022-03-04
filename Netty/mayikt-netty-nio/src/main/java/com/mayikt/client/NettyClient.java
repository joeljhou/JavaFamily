package com.mayikt.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * @author 周宇
 * @create 2021-09-21 22:17
 */
public class NettyClient {
    public static void main(String[] args) {
        //创建nioEventLoopGroup
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        //NioSocketChannel 标记当前使用为客户端
        bootstrap.group(group).channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress("127.0.0.1", 8080))
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        //处理每个请求的hanlder
                        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                        channel.pipeline().addLast(new StringEncoder());
                        channel.pipeline().addLast(new ChientHandler());
                    }
                });

        //发起同步请求
        try {
            ChannelFuture sync = bootstrap.connect().sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
