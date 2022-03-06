package com.xhgj.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

/**
 * @author 周宇
 * @create 2022-03-06 13:58
 * 生产端
 */
public class Send {

    private final static String QUEUE_NAME = "hello";

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
            //4.声明（创建）一个队列
            //幂等的,只有当它不存在时才会创建它
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            //5.通过channel发送数据
            String message = "Hello RabbitMQ!";
            //1.exchange  2.routingKey
            //exchange为null时，默认走(AMQP default) 按照routingKey名字相同的队列进行匹配，如果有，就将消息投递过去
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] 发送 '" + message + "'");
        }
    }
}
