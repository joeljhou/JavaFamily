package com.xhgj.adv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @author 周宇
 * @create 2022-02-28 2:31
 * Redis事务属于服务端行为
 */
@Component
public class RedisTransaction {

    public final static String RS_TRANS_NS = "rts:";

    @Autowired
    private JedisPool jedisPool;

    public List<Object> transaction(String... watchKeyts) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            if (watchKeyts.length > 0) {
                /*使用watch功能*/
                String watchResult = jedis.watch(watchKeyts);
                if (!"OK".equals(watchResult)) {
                    throw new RuntimeException("执行watch失败：" + watchResult);
                }
            }
            Transaction multi = jedis.multi();
            multi.set(RS_TRANS_NS + "testa1", "a1");
            multi.set(RS_TRANS_NS + "testa2", "a2");
            multi.set(RS_TRANS_NS + "testa3", "a3");
            List<Object> execResult = multi.exec();
            if (watchKeyts == null) {
                throw new RuntimeException("事物无法执行，监视的key被修改" + watchKeyts);
            }
            System.out.println(execResult);
            return execResult;
        } catch (Exception e) {
            throw new RuntimeException("执行Redis事务失败！" + e.getMessage());
        } finally {
            if (watchKeyts.length > 0) {
                jedis.unwatch();/*前面如果watch了，这里就要unwatch*/
            }
            if (jedis != null) {
                jedis.close();
            }
        }
    }



}
