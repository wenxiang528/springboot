package com.example.demo.test.port;

import lombok.Data;

@Data
public class Employee implements Comparable<String>{
//    工资
    private  double salary;


    @Override
    public int compareTo(String other) {
        return Double.compare(salary,Double.valueOf(other));
    }
}
