package com.utils;

import org.junit.Test;
import org.springframework.scheduling.annotation.Scheduled;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author chenyantao
 * @date 2017/11/23.
 */
public class RedisUtil {
    private Jedis jedis = new Jedis("47.91.77.217",6379);

    @Test
    public void test1(){
        List list = new ArrayList<>();
        list.add("xu");
        setList("student",list);
        System.out.println(getList("student"));
    }

    @Scheduled()

    public Map<String,String> getMap(String mapKey){
         Map<String,String> resultMap = new HashMap<>();
         Iterator<String> iter=jedis.hkeys(mapKey).iterator();
         while (iter.hasNext()){
             String key = iter.next();
             resultMap.put(key,jedis.hmget("user",key).get(0));
         }
        return resultMap;
    }

    public void setMap(String key,Map<String, String> map){
        jedis.hmset(key,map);
    }

    public void setList(String key,List<String> list){
        //开始前，先移除所有的内容

        for (String s : list) {
            jedis.rpush(key,s);
        }
    }

    public List<String> getList(String key){
        return jedis.lrange(key,0,-1);
    }

    //清空redis
    public String flushDb(){
        return jedis.flushDB();
    }

    //删除指定key value
    public void delKey(String key){
         jedis.del(key);
    }
}
