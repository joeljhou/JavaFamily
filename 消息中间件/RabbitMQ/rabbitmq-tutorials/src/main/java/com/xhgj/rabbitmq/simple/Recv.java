package com.xhgj.rabbitmq.simple;

import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;

/**
 * @author 周宇
 * @create 2022-03-06 13:41
 * 消费端
 */
public class Recv {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        //1.创建一个ConnectionFactory，并进行配置
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("82.156.210.141");
        factory.setUsername("rabbit");
        factory.setPassword("rabbit");
        factory.setPort(5672);
        factory.setVirtualHost("/");

        //2.通过连接工厂创建连接  不需要关闭
        //3.通过connection创建一个信道Channel  不需要关闭
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //4.声明（创建）一个队列
        //1.队列名称 2.是否持久化(服务器重启后继续有效) 2.声明独占(仅限于此连接,负载均衡模式下保证顺序) 4.自动删除(服务器将在不再使用时将其删除) 5.扩展参数
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] 等待消息。 要退出，请按 CTRL+C");

        //5.创建消费者
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] 已收到 '" + message + "'");
        };

        //5.设置Channel 监听队列
        //1.队列名称 2.是否自动确认 3.消费者要监听的队列
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

    }
}
