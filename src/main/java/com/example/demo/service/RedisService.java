package com.example.demo.service;

public interface RedisService {

    /**
     * 基于redis的单点登录设计及实现
     * 1)用户登录成功以后将登录状态等信息存储到redis
     * 2)用户携带token去访问资源,资源服务器要基于token从redis查询用户信息
     */
    void getToken(String userName,String password) throws InterruptedException;




}
