package com.example.demo.test.excelTest;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * jxl读取Excel
 */
public class ReadExcelByJxl {
    public static void main(String[] args) throws Exception {
        //根据文件位置找到需要读取的excel文件
        File Inputfile = new File("E:\\测试表格.xls");

        //根据路径生成 FileInputStream字节流
        FileInputStream fileInputStream = new FileInputStream(Inputfile);

        //将FileInputStream转换为Workbook
        Workbook workbook;
        workbook = Workbook.getWorkbook(fileInputStream);

        // 默认获取第一张工作表，可以自定义
        Sheet sheet = workbook.getSheet(0);

        // 循环获取每一行数据 因为默认第一行为标题行，我们可以从 1 开始循环，如果没有标题行，i从 0 开始
        // sheet.getRows() 获取总行数


        for (int i = 1; i < sheet.getRows(); i++) {
            // 获取第一列的第 i 行信息 sheet.getCell(列，行)，下标从0开始
            String name = sheet.getCell(0, i).getContents();
            // 获取第二列的第 i 行信息
            String age = sheet.getCell(1, i).getContents();
            //获取第三列的第i行信息
            String sex=sheet.getCell(2, i).getContents();
            System.out.println("姓名:"+name+",年龄:"+age+",性别:"+sex);
            System.out.printf("");
        }


    }
}
