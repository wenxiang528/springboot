package com.example.demo.test.runtime;


public class ProcessTest {

    public static void main(String[] args) throws Exception {
//       常用的windows 应用软件命令
//       1、regedit：打开注册表编辑器
//　　　　2、control：打开控制面板
//　　　　3、msconfig：打开系统配置
//　　　　4、gpedit.msc：打开本地组策略
//　　　　5、explorer：打开资源管理器
//　　　　6、taskmgr：任务管理器
//　　　　8、osk：打开屏幕键盘
//　　　　9、calc：打开计算器
//　　　　10、mspaint：调出画图软件
//　　　　11、dxdiag：查看电脑详细配置信息
//　　　　12、mstsc：打开远程桌面连接
//　　　　13、systeminfo：查看计算机基本信息
//　　　　14、notepad：打开记事本


//        创建进程方法

//        一、通过Runtime类创建进程
//        Runtime  run=Runtime.getRuntime()
//        打开记事本
//        run.exec("notePad");

//      二、通过ProcessBuilder类创建进程
//        打开记事本
            ProcessBuilder processBuilder = new ProcessBuilder("notepad");
        processBuilder.start();

    }

}
