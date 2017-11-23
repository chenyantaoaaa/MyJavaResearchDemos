package com.utils;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

/**
 * @author chenyantao
 * @date 2017/11/23.
 */
public class RedisUtil {
    private Jedis jedis = new Jedis("47.91.77.217",6379);

    @Test
    public void test1(){
//        jedis.set("chen","sb2");
//        System.out.println(jedis.get("chen"));
        mapSet();
    }

    public void mapSet(){
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("name", "chenyantao");
//        map.put("password", "123");
//        map.put("age", "26");
//        jedis.hmset("user", map);

        // map key的个数
        System.out.println("map的key的个数" + jedis.hlen("user"));

        // map key
        System.out.println("map的key" + jedis.hkeys("user"));

        // map value
        System.out.println("map的value" + jedis.hvals("user"));

        // (String key, String... fields)返回值是一个list
        List<String> list = jedis.hmget("user", "age", "name","password");
        System.out.println(list);

//        jedis.hdel("user", "age");
//
//        System.out.println("删除后map的key" + jedis.hkeys("user"));
    }

    public void setMap(String key,Map<String, Object> map){
//        jedis.hmset(key, map);
    }
}
