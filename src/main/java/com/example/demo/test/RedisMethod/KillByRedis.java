package com.example.demo.test.RedisMethod;

import redis.clients.jedis.Jedis;

/**
 * @desc 简易秒杀队列方法实现
 */
public class KillByRedis {

        //Redis存放抢购信息
    public static void enque(String msg){//入队
        Jedis jedis=new Jedis("127.0.0.1",6379);
        jedis.lpush("queue",msg);
        jedis.close();
    }

    //底层异步出队(基于这个消息,生成订单,扣减库存,...)
    public static String deque(){//出队
        Jedis jedis=new Jedis("127.0.0.1",6379);
        String result=jedis.rpop("queue");
        jedis.close();
        return result;
    }

}
