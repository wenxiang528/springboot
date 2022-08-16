package com.example.demo.test.clone;

import org.springframework.beans.BeanUtils;

public class clone1 {
    public static void main(String[] args) throws Exception {
//        对象克隆 Object类的clone方法
//        默认情况下clone是属于Object类下的protected方法 对象需要实现Cloneable接口
//        将clone方法重新定义为public 再调用super.clone()
//        下面是employee的拷贝实例
        Employee original=new Employee();
        original.setName("Peter");
        original.setSalary(5000);
//        对象克隆
//        Employee copy=original.clone();
//        copy.raiseSalary(10);
//        System.out.println(copy.toString());

        Employee e1=new Employee();
        e1.setName("Jim");
        e1.setSalary(3000);

//        Spring包内的克隆方法
//        将original的属性值克隆给e1
      BeanUtils.copyProperties(original,e1);
        System.out.println(e1);

    }
}
