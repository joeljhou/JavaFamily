package com.xhgj.rabbitmq.exchange.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author 周宇
 * @create 2022-03-15 14:56
 */
public class Producer4DirectExchange {
    public static void main(String[] args) throws Exception {
        //1.创建一个ConnectionFactory，并进行配置
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("82.156.210.141");
        factory.setUsername("rabbit");
        factory.setPassword("rabbit");
        factory.setPort(5672);
        factory.setVirtualHost("/");

        //2.通过连接工厂创建连接  需要关闭
        //3.通过connection创建一个信道Channel  需要关闭
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            //4.声明
            String exchangeName = "test_direct_exchange";
            String routingKey = "test.direct";

            //5.通过channel发送数据
            String message = "Hello World RabbitMQ 4 Direct Exchange Message...";
            channel.basicPublish(exchangeName, routingKey, null, message.getBytes());
            System.out.println(" [x] 发送 '" + message + "'");
        }
    }
}
