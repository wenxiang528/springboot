package com.example.demo.test.array;

import javax.swing.*;
import java.util.Arrays;

public class Array1 {
    public static void main(String[] args) {
        int[] old={6,3,4,1};

//        数组的拷贝 Arrays.copyOf
//        第一个参数 要拷贝的数组
//        第二个参数 新数组的长度 一般用于扩充数组
        int[] copiedNumbers= Arrays.copyOf(old,2*old.length);
        System.out.println(Arrays.toString(copiedNumbers));

//        对数值型数组进行排序 可以用sort方法
          Arrays.sort(old);
          System.out.println(Arrays.toString(old));

//          比较两个数组的大小和各个下标元素的值
          System.out.println(Arrays.equals(old,copiedNumbers));







    }
}
