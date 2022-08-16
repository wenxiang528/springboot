package com.example.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Teacher;
import com.example.demo.service.ITeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 薛之谦
 * @since 2021-10-29
 */
@RestController
@RequestMapping("/teacher")
@Transactional
@Api
public class TeacherController {
    @Resource
    private ITeacherService teacherService;

    @PostMapping("/selectAll")
    Teacher selectAll() {
        return null;
    }



    @PostMapping("/getTeacherPage")
    IPage<Teacher> get (HttpServletRequest request){
        String tsex=(String) request.getParameter("tsex");
        Map<String,Object> map=new HashMap();
        map.put("tsex",request.getParameter("tsex"));
        map.put("page",request.getParameter("page"));
        map.put("pageSize",request.getParameter("pageSize"));
        IPage<Teacher> result =teacherService.selectUserPage(map);
        return result;
    }


}

