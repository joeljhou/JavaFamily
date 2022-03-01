package com.xhgj.adv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Arrays;

/**
 * @author 周宇
 * @create 2022-03-02 2:21
 */
@Component
public class RedisLua {

    public final static String RS_LUA_NS = "rla:";

    public final static String LUA_SCRIPTS =
            "local num = redis.call('incr',KEYS[1])\n" +
                    "if tonumber(num) == 1 then\n" +
                    "\tredis.call('expire',KEY[1],ARGV[1])\n" +
                    "\treturn 1\n" +
                    "elseif tonumber(num) > tonumber(ARGV[2]) then\n" +
                    "else \n" +
                    "\treturn 1\n" +
                    "end";


    @Autowired
    private JedisPool jedisPool;

    public String loadScripts() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String sha = jedis.scriptLoad(LUA_SCRIPTS);
            return sha;
        } catch (Exception e) {
            throw new RuntimeException("加载脚本失败！" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String ipLimitFlow(String ip) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String result = jedis.evalsha("11b521d7f41f95d4f9a5254bd03e72d77912e4da", Arrays.asList(RS_LUA_NS + ip), Arrays.asList("60", "2")).toString();
            return result;
        } catch (Exception e) {
            throw new RuntimeException("执行脚本失败！" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}
