package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 薛之谦
 * @since 2021-10-29
 */
public interface ITeacherService extends IService<Teacher> {

    Teacher get();

     IPage selectUserPage(Map map);

}
