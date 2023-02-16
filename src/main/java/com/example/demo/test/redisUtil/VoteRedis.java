package com.example.demo.test.redisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

import java.util.Set;

/**
 * 基于Redis的投票实现方法
 */
public class VoteRedis {
    /**
     * 执行投票操作
     * @param activityId
     * @param userId
     */
    public static void doVote(String activityId, String userId){
        //1.建立连接
        Jedis jedis=new Jedis("127.0.0.1", 6379);
        //2.执行投票
        //2.1检查是否投过票
        //判断元素是否在集合中
        Boolean flag = jedis.sismember(activityId, userId);
        //2.2执行投票或取消投票
        if(flag){
            //假如已经投过票,再投票就取消投票
            //删除集合中的某个元素
            jedis.srem(activityId, userId);
        }else{
            //没有投过票则执行投票
            //向集合中添加元素
            jedis.sadd(activityId, userId);
        }
        //设置键的有效期为5秒
        jedis.expire(activityId,5);
        //3.释放资源
        jedis.close();

    }
    /**
     * 获取指定活动的投票总数
     * @param activityId
     * @return
     */
    public static Long doCount(String activityId){
        //1.建立连接
        Jedis jedis=new Jedis("127.0.0.1", 6379);
        //2.获取当前活动的总票数
        //返回集合的长度
        Long count=jedis.scard(activityId);
        //3.释放资源
        jedis.close();
        return count;
    }
    /**
     * 获取哪些人执行了这个活动的投票
     * @param activityId
     * @return
     */
    public static Set<String> doGetMembers(String activityId){
        //1.建立连接
        Jedis jedis=new Jedis("127.0.0.1", 6379);
        //2.获取当前活动的总票数
        //获取集合中的所有元素
        Set<String> smembers = jedis.smembers(activityId);
        //3.释放资源
        jedis.close();
        return smembers;
    }




}
