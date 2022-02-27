package com.xhgj.redismq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author 周宇
 * @create 2022-02-27 2:07
 * 基于ZSet实现延时队列，将过期时间作为分数
 */
@Component
public class ZSetVer {
    public final static String RS_ZS_MQ_NS = "rzsm:";

    @Autowired
    private JedisPool jedisPool;

    /**
     * 生产者：消息的发送，实际生产中，相关参数
     */
    public void producer() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            for (int i = 0; i < 5; i++) {
                String order_id = "00000000" + i;
                double score = System.currentTimeMillis() + (i * 1000);
                //score 和时间有关
                jedis.zadd(RS_ZS_MQ_NS + "orderId", score, order_id);
                System.out.println("生产订单:" + order_id + " 当前时间:" +
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                System.out.println(i + "秒后执行");
            }
        } catch (Exception e) {
            throw new RuntimeException("生产消息失败！" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 消费者,取订单
     */
    public void consumerDelayMessage() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            while (true) {
                //查看有没有元素满足要求
                Set<String> order = jedis.zrangeByScore(RS_ZS_MQ_NS + "orderId", 0,
                        System.currentTimeMillis(), 0, 1);
                //没有元素就休眠
                if (order == null || order.isEmpty()) {
                    System.out.println("当前没有等待的任务");
                    try {
                        TimeUnit.MICROSECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                String s = order.iterator().next();   //拿到元素
                if (jedis.zrem(RS_ZS_MQ_NS + "orderId", s) > 0) {
                    /*业务处理*/
                    System.out.println(s);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("消费消息失败！" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}
