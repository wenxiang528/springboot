package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 薛之谦
 * @since 2021-10-29
 */
public interface TeacherMapper extends BaseMapper<Teacher> {
   List<Teacher> selectPageVo(IPage page, @Param("tsex") String tsex);

   Teacher  chooseSelect(Map param);



}
