package com.example.demo.test.clone;

import lombok.Data;

@Data
public class Employee implements  Cloneable {

    private  String name;

    private  int salary;


    public void raiseSalary(int salary){
        this.salary=this.salary+salary;
    }

//    重写clone方法 将protected改为public
    @Override
    public Employee clone() throws CloneNotSupportedException {
        return (Employee)super.clone();
    }
}
