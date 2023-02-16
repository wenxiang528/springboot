package com.example.demo.service.impl;

import com.example.demo.service.RedisService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.UUID;

@Service
public class RedisServiceImpl implements RedisService {
    /**
     * 基于redis的单点登录设计及实现
     * 1)用户登录成功以后将登录状态等信息存储到redis
     * 2)用户携带token去访问资源,资源服务器要基于token从redis查询用户信息
     */
    @Override
    public void getToken(String userName, String password) throws InterruptedException {
        //1.登录操作(用户身份认证)
        String token=doLogin(userName, password);
        System.out.println(token);
//        //此处休眠4秒 token键在Login方法中设置的有效时间为3秒，所以token键会过期 所以下面会显示请先登录
//        Thread.sleep(4*1000);
        //2.携带token访问资源服务器
        Object result=doGetResource(token);
        System.out.println(result);
    }





    static String doLogin(String username,String password){
        //1.检验数据的合法性(判定用户名,密码是否为空,密码的长度,是否有数字字母特殊符号构成)
        if(username==null||"".equals(username))
            throw new IllegalArgumentException("用户不能为空");
        //2.基于用户名查询用户信息,并判定密码是否正确 此处默认用户名为jack 密码为123456
        if(!"jack".equals(username))
            throw new RuntimeException("此用户不存在");
        if(!"123456".equals(password))
            throw new RuntimeException("密码不正确");
        //3.用户存在且密码正确,将用户信息写入到redis
        Jedis jedis=new Jedis("127.0.0.1", 6379);
        String token= UUID.randomUUID().toString();
        jedis.hset(token, "username", username);
        jedis.hset(token, "permission", "sys:resource:create");
        jedis.expire(token, 3);//设置key的有效时间
        jedis.close();
        //4.将token返回给客户端(将来使用response对象响应到客户端).
        return token;
    }

    /**
     * 演示资源访问过程
     * 1)允许匿名访问(无需登录)
     * 2)登录后访问(认证通过了)
     * 3)登录后必须有权限才可以访问
     */
    static Object doGetResource(String token){
        //1.校验token是否为空
        if(token==null)
            throw new IllegalArgumentException("请先登录");
        //2.基于token查询redis数据,假如有对应数据说明用户登录了
        Jedis jedis=new Jedis("127.0.0.1", 6379);
        String username=jedis.hget(token, "username");
        if(username==null)
            throw new RuntimeException("登录超时,请重新登录");
        String permission=jedis.hget(token, "permission");
        jedis.close();
        //3.检查用户是否有访问资源的权限,假如有则允许访问
        if(!"sys:resource:create".equals(permission))
            throw new RuntimeException("你没有权限访问这个资源");
        //4.返回要访问的资源.
        return "访问成功";
    }


}
