package com.example.demo.test.port;

public class Test {
    public static void main(String[] args) {
        Employee employee=new Employee();
        employee.setSalary(2000);
        System.out.println(employee.compareTo("3000"));


    }
}
