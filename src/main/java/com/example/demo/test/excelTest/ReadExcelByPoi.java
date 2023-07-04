package com.example.demo.test.excelTest;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;

public class ReadExcelByPoi {
    public static void main(String[] args) throws Exception {
        String path="E:\\测试表格.xls";
        // 获取文件输入流
        InputStream inputStream = new FileInputStream(path);
        // 定义一个org.apache.poi.ss.usermodel.Workbook的变量
        Workbook workbook =null;
        // 截取路径名 . 后面的后缀名，判断是xls还是xlsx
        // 如果这个判断不对，就把equals换成 equalsIgnoreCase()
        if (path.substring(path.lastIndexOf(".") + 1).equals("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (path.substring(path.lastIndexOf(".") + 1).equals("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        }

        // 获取第一张表
        Sheet sheet = workbook.getSheetAt(0);
        // sheet.getPhysicalNumberOfRows()获取总的行数
        // 循环读取每一行
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            // 循环读取每一个格
            Row row = sheet.getRow(i);
            // row.getPhysicalNumberOfCells()获取总的列数
            for (int index = 0; index < row.getPhysicalNumberOfCells(); index++) {
                // 获取数据，但是我们获取的cell类型
                //获取第i+1行的第index+1列的内容
                Cell cell = row.getCell(index);
                // 转换为字符串类型
                cell.setCellType(CellType.STRING);
                // 获取得到字符串
                String str = cell.getStringCellValue();
                System.out.println(str);
            }


        }
    }
}
