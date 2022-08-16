package com.example.demo.test;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.Teacher;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.spring.web.json.Json;

import java.math.BigDecimal;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Test {
    public static void main(String[] args) throws Exception {
//        System.out.println(isIpv6("10.168.13.29"));
//        System.out.println(isIpv6("2001:db8:2de:0:0:0:0:e13"));


//        String a="2";
//        System.out.println(a.matches("[1-9]\\d*"));
//        System.out.println(a.matches("^[1-9]*$")&&a.compareTo("0")>0);





          List<Map<String,Object>> map=new ArrayList<>();

            System.out.println(JSON.toJSONString(map));









    }


    public static boolean isIpv6(String add) {
        try {
            final InetAddress inetAddress = InetAddress.getByName(add);
            return inetAddress instanceof Inet6Address;
        } catch (Exception e) {
            return false;
        }
    }

}
