package com.example.demo;

import com.example.demo.entity.Teacher;
import com.example.demo.test.redisUtil.VoteRedis;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.build.BuildLogger;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@SpringBootTest
@Slf4j
public class RedisTests {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private RedisTemplate redisTemplate;

    /*测试是否可以连通redis*/

    @Test
    public void testGetConnection(){
        Jedis jedis=new Jedis("127.0.0.1",6379);
        String result = jedis.ping();
        System.out.println(result);
    }


    /*测试字符串操作 */
    @Test
    public void testStringOper01(){
        //1.建立连接
        Jedis jedis=new Jedis("127.0.0.1",6379);
        //2.执行redis数据操作(增删改查)
        //2.1新增数据
        jedis.set("id", "100");
        jedis.set("name", "tony");
        //2.2修改数据
        jedis.incr("id");
        jedis.incrBy("id", 100);//201
        jedis.set("name", "Mike");//Mike
        //2.3查询数据
        String id = jedis.get("id");
        String name = jedis.get("name");
        System.out.println("id="+id+";name="+name);
        //2.4删除数据
        jedis.del("name");
        //3.释放资源
        jedis.close();
    }

    /*基于hash类型将testStringOper02中对象写入到redis，
     * 并且尝行查询，修改，删除等操作。
     */
    @Test
    public void testHash01(){
        //1.建立连接
        Jedis jedis=new Jedis("127.0.0.1",6379);
        //2.执行hash数据操作
        //2.1新增数据
        String key= UUID.randomUUID().toString();
        jedis.hset(key, "id", "500");
        jedis.hset(key, "name", "Jack");
        //2.2修改数据数据
        jedis.hset(key, "name", "Jim");
        //2.3查询数据
        Map<String, String> map = jedis.hgetAll(key);
        System.out.println(map);
        //2.4删除数据
        //jedis.del(key);
        //设置key的过期时间为3秒
        jedis.expire(key, 3);
        try {
            Thread.sleep(4*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<String, String> map2 = jedis.hgetAll(key);
        System.out.println(map2);
        //3.释放资源
        jedis.close();
    }

    //set类型操作
    @Test
    public void testSetOper01() {
        //1.连接redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //2.朋友圈点赞
        jedis.sadd("count", "1", "1", "2");
        //3.取出点赞数
        Set<String> set = jedis.smembers("count");
        System.out.println(set);
        //4.释放资源
        jedis.close();
    }


    //RedisTemplate的简单使用

    /*测试是否能够连通redis*/
    @Test
    void testConnection(){
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        String result = connection.ping();
        System.out.println(result);
    }


    @Test
    void testString(){
        //1.获取字符串操作对象(ValueOperations)
        ValueOperations vo = redisTemplate.opsForValue();
        //2.操作redis数据
        vo.set("x", 100);
        Object x = vo.get("x");
        System.out.println(x);
        //vo.increment("x");//不可以
        Long y = vo.increment("y");
        //自增1
        y=vo.increment("y");
        //Object y = vo.get("y");//不可以
        System.out.println(y);
        //存储key/value，设置key的有效期
        vo.set("z", "100", Duration.ofSeconds(10));
    }

    //序列化存储字符串
    @Test
    void testString02(){
        //1.获取字符串操作对象(ValueOperations)
        ValueOperations vo = redisTemplate.opsForValue();
        //2.按默认序列化方式存储数据
        String token= UUID.randomUUID().toString();
        vo.set(token,"admin");
        //3.指定序列方式进行数据存储
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.string());
        vo.set(token,"Mike");
        //4.更新数据(假如有对应的key则直接进行覆盖)
        vo.set(token, "Jack");
        Object value = vo.get(token);
        System.out.println(value);
        //5.删除数据(存数据时给定有效期-生产环境必须设置)
        vo.set("permissions", "sys:res:update",Duration.ofSeconds(5));
    }


    //RedisTemplate散列类型数据存储及调用
    @Test
    void testHashOper01() {
        //1.获取Hash操作对象(ValueOperations)
        HashOperations ho = redisTemplate.opsForHash();
        //2.以hash类型存储数据
        ho.put("blog", "id", 100);
        ho.put("blog", "title", "redis");
        //3.获取数据
        Object id = ho.get("blog", "id");
        Object title = ho.get("blog", "title");
        System.out.println("id=" + id + ";title=" + title);
        Map blog = ho.entries("blog");//取key对应的所有值。
        System.out.println(blog);

    }

    /*通redisTemplate将一个类的对象写入到redis数据库*/
    /**
    * 方案1：基于ValueOperations对象实现数据存取
    * 方案2：基于HashOperations对象实现数据存储
    */
    @Test
    void testHashOper02() throws JsonProcessingException {
        //1.基于ValueOperations存取Teacher对象
        //获取数据操作对象(ValueOperations,HashOperations)
        ValueOperations vo = redisTemplate.opsForValue();
        Teacher teacher=new Teacher();
        teacher.setTname("Tom");
        teacher.setTage(32);
        vo.set("teacher-jack", teacher);//序列化
        Teacher t = (Teacher)vo.get("teacher-jack");//反序列化
        System.out.println(t);

        //2.基于HashOperations存取Teacher对象
        HashOperations ho = redisTemplate.opsForHash();
        ObjectMapper objectMapper=new ObjectMapper();//jackson
        String jsonStr=objectMapper.writeValueAsString(teacher);
        Map map = objectMapper.readValue(jsonStr, Map.class);
        ho.putAll("teacher-mike", map);
        map=ho.entries("teacher-mike");
        System.out.println(map);
    }

    //RedisTemplate存放列表类型数据
    @Test
    void testListOper(){
        //向list列表放数据
        ListOperations listOperations = redisTemplate.opsForList();
        listOperations.leftPush("lstKey1", "100"); //lpush
        listOperations.leftPushAll("lstKey1", "200","300");
        listOperations.leftPush("lstKey1", "100", "105");
        listOperations.rightPush("lstKey1", "700");
        Object value= listOperations.range("lstKey1", 0, -1);
        System.out.println(value);
        //从list列表取数据
        Object v1=listOperations.leftPop("lstKey1");//lpop
        System.out.println("left.pop.0="+v1);
        value= listOperations.range("lstKey1", 0, -1);
        System.out.println(value);
    }

    //RedisTemplate存放集合类型数据
    @Test
    void testSetOper(){
        SetOperations setOperations=redisTemplate.opsForSet();
        setOperations.add("setKey1", "A","B","C","C");
        Object members=setOperations.members("setKey1");
        System.out.println("setKeys="+members);
        System.out.println(setOperations.size("setKey1"));

    }



    //简易投票系统
    @Test
    void testVote(){
        String activityId="100";
        String userId1="1";
        String userId2="2";
        String userId3="3";
        //执行投票动作
        VoteRedis.doVote(activityId, userId1);
        VoteRedis.doVote(activityId, userId2);
        VoteRedis.doVote(activityId, userId3);
//        VoteRedis.doVote(activityId, userId3);
        //获取投票的总票数
        Long aLong = VoteRedis.doCount(activityId);
        System.out.println(aLong);
        //获取参与投票的成员
        Set<String> members= VoteRedis.doGetMembers(activityId);
        System.out.println(members);



    }

}
