package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Teacher;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.util.Condition;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.valves.rewrite.RewriteCond;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 薛之谦
 * @since 2021-10-29
 */
@Service
@Slf4j
//事务管理
//@Transactional
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {
@Resource
private TeacherMapper teacherMapper;
    @Override
    public Teacher get() {
        return null;
    }

    public IPage selectUserPage(Map map) {
       IPage<Teacher> page= new Page<>(Integer.parseInt((String)map.get("page")),Integer.parseInt((String)map.get("pageSize")));
       String tsex=(String) map.get("tsex");
        List<Teacher> list =baseMapper.selectPageVo(page,tsex);
        return page.setRecords(list);
    }

}
