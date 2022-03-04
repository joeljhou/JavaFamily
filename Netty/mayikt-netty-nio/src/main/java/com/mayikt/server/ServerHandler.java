package com.mayikt.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author 周宇
 * @create 2021-09-21 22:01
 */
public class ServerHandler extends SimpleChannelInboundHandler {
    /**
     * 获取数据
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        String request = byteBuf.toString(CharsetUtil.UTF_8);

        //响应代码
        System.out.println("request：" + request);
        ctx.writeAndFlush(Unpooled.copiedBuffer("平均突破3万月薪\n", CharsetUtil.UTF_8));


        //String[] split = request.split("\n");
        //for (int i = 0; i < split.length; i++) {
        //    //响应代码
        //    ctx.writeAndFlush(Unpooled.copiedBuffer("平均突破3万月薪", CharsetUtil.UTF_8));
        //    System.out.println("request：" + split[i]);
        //}
    }
}
