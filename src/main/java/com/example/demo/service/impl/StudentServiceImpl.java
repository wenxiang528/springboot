package com.example.demo.service.impl;

import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 薛之谦
 * @since 2021-10-29
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {


}
