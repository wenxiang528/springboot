package com.example.demo.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//MybatisPlus 的分页配置
    @Configuration
    @MapperScan("com.example.demo.mapper")
    public class MybatisPlusConfig {
        /**
         * IPage的分页使用的是拦截器，属于物理分页，好处就是处理大量数据时，查询速度快。
         * @return
         */
        @Bean
        public MybatisPlusInterceptor mybatisPlusInterceptor() {
            MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
            //向Mybatis过滤器链中添加分页拦截器
            interceptor.addInnerInterceptor(new
                    PaginationInnerInterceptor(DbType.MYSQL));
            return interceptor;
        }

        @Bean
        public ConfigurationCustomizer configurationCustomizer() {
            return configuration -> configuration.setUseDeprecatedExecutor(false);
        }
    }

