package com.example.demo.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//JdbcTemplate 查询对象时用
public class TeacherRowMapper implements RowMapper<Teacher> {
    @Override
    public Teacher mapRow(ResultSet resultSet, int i) throws SQLException {
        Teacher teacher=new Teacher();
       teacher.setTid(resultSet.getInt("tid"));
       teacher.setTname(resultSet.getString("tname"));
       teacher.setTage(resultSet.getInt("tage"));
       teacher.setTlvl(resultSet.getString("tlvl"));
       teacher.setTsex(resultSet.getString("tsex"));

        return teacher;
    }
}
