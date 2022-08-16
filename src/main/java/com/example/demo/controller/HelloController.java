package com.example.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Api(value = "desc of class")
@RestController
public class HelloController {
        /* 方法注解 */
        @ApiOperation(value = "hello方法接口文档", notes = "")
        @PostMapping(value="/hello")
        public Object hello(/* 参数注解 */ @ApiParam(value = "姓名" , required=true ) @RequestParam String name) {
            return "Hello " + name + "!";
        }
    }
