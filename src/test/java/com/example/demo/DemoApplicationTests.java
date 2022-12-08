package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.StuJson;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.TeacherRowMapper;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.service.ITeacherService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;


import javax.annotation.Resource;
import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class DemoApplicationTests {
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private ITeacherService teacherService;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private  RedisTemplate redisTemplate;




    @Test
//    查询教师表的所有数据
    public void testSelect() {
//        带条件的查询
        QueryWrapper wrapper1 = new QueryWrapper<>().eq("tsex", "男");
        List<Teacher> teachers = teacherMapper.selectList(wrapper1);
        log.info("-------------------性别为男的教师信息为-------------------------");
        for (Teacher t : teachers) {
            System.out.println(t);
        }
//        QueryWrapper wrapper = new QueryWrapper<>().eq("tname", "于洪渤");
//        Teacher teacher = teacherMapper.selectOne(wrapper);
//        log.info("--------------------------yhb大撒比-------------------");
//        System.out.println(teacher);
    }
//
    @Test
//    插入
    public void insert() {
//        Teacher teacher = new Teacher();
//        teacher.setTid(30023);
//        teacher.setTname("于洪渤");
//        teacher.setTage(500);
//        teacher.setTlvl("垃圾教师");
//        teacher.setTsex("人妖");
//        teacherMapper.insert(teacher);
    }
//
//    @Test
////    更新
//    public void update() {
//
//        Teacher teacher = new Teacher();
//        teacher.setTid(30012);
//        teacher.setTlvl("次级教师");
////        QueryWrapper里面的是更新条件  Entity是更新的值
//        QueryWrapper wrapper = new QueryWrapper<>().eq("tid", 30008);
//        log.info("---------------------开始更新-------------------");
////        这条更新语句表示 更新教师表tid为30011的数据 数据的tid更新为30012 tlvl更新为"次级教师"
//        teacherMapper.update(teacher, wrapper);
//    }
//
//
    @Test
//    删除
    public void delete() {
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("tid", 30012);
        log.info("-----------------------开始删除-----------------------");
        teacherMapper.delete(wrapper);

    }
//
//    @Test
//    void Random() {
//        for (int i = 0; i < 4; i++) {
//            char num = (char) (65 + new Random().nextInt(64));
//            System.out.println("随机数为" + num);
//            System.out.printf("");
////            生成长度为6位的随机字符串（包括数字）
//            String fileName = RandomStringUtils.randomAlphanumeric(6);
//            System.out.println(fileName);
//            System.out.printf("");
//        }
//    }
//
    @Test
    void choose() {
        Map<String, Object> map = new HashMap<>();
        map.put("tid", 30001);
        map.put("tname", "胡美丽");
        map.put("tage", 500);
        Teacher teacher = teacherMapper.chooseSelect(map);
        System.out.println(teacher);
    }
//
//    @Test
//    void hashsMap() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("age", "25");
//        System.out.println(map.get("name"));
//    }
//
//
//    @Test
//    void hashsSet() {
//        //hashSet有自动去重特性 且加入的元素是无序的
//        Set<Map<String,Object>> set=new HashSet<>();
//
//        Map<String,Object> map=new HashMap<>();
//        Map<String,Object> map1=new HashMap<>();
//        Map<String,Object> map2=new HashMap<>();
//        Map<String,Object> map3=new HashMap<>();
//        Map<String,Object> map4=new HashMap<>();
//        map.put("age","20");
//        map.put("name","wx");
//        map1.put("age","20");
//        map1.put("name","whp");
//        //map2、map3的数据只会添加到Set一次
//        map2.put("age","25");
//        map3.put("age","25");
//       set.add(map);
//       set.add(map1);
//       set.add(map2);
//
//
//        System.out.println(set);
//    }
//
//
//    @Test
//    void Json(){
//        String jsonString = "{name:'Antony',age:'12',sex:'male',telephone:'88888'}";
//        //JSON字符串转化为对象
//        StuJson stuJson = JSON.parseObject(jsonString, StuJson.class);
//        System.out.println(stuJson);
//
//
//        //对象转换为JSON字符串
//        String jsonStr = JSON.toJSONString(stuJson);
//        System.out.println(jsonStr);
//    }
//
//  @Test
//    void  JdbcTemplate(){
//      log.info("----------------JDBCTEMPLATE开始执行-------------------");
//      //插入sql
//      //String sql="insert into teacher values(?,?,?,?,?)";
//      //更新sql
//      //String sql="update teacher set tname='测试1' where tname='测试'"
//
//        //执行语句
//        //jdbcTemplate.update(sql,30013,"测试","男",26,"高级教师");
//
//      //查询单个值
//      String sql1="select tname from teacher where tid=?";
////      String tname= jdbcTemplate.queryForObject(sql1,String.class,30012);
////      System.out.println(tname);
//
//      //查询一个对象
////      String sql2="select * from teacher where tid=?";
////      Teacher teacher=jdbcTemplate.queryForObject(sql2,new TeacherRowMapper(),30012);
////      System.out.println(teacher);
//
////      查询多个对象
//      String sql3="select * from teacher";
//      List<Teacher> list=jdbcTemplate.query(sql3,new TeacherRowMapper());
//      System.out.println(list);
//
//
//      log.info("----------------JDBCTEMPLATE执行结束 -------------------");
//
//  }

     @Test
    public  void redisTest(){
        redisTemplate.opsForValue().set("key1","233");
         System.out.println(redisTemplate.opsForValue().get("key1"));

     }
}
