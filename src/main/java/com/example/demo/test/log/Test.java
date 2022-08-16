package com.example.demo.test.log;

import java.io.IOException;
import java.util.logging.*;

public class Test {
//    获取(不存在时新建)日志记录器
    private static  final  Logger myLogger=Logger.getLogger("com.example.demo");

    public static void main(String[] args) throws IOException {
//        取消所有日志
//        Logger.getGlobal().setLevel(Level.OFF);

//        全局日志记录器 打印日志
//        Logger.getGlobal().info("File ->Open menu item selected");

//        日志记录方法 默认只记录INFO级别以上的日志
//        myLogger.info("九折");
//        myLogger.warning("警告");

//        也可以用log方法并指定级别
//        myLogger.log(Level.INFO,"就这");

//        日志本地化
//日志记录在C:\Users\lenovo java0.log文件下
        Logger logger=Logger.getLogger("com.example.demo");
        logger.setLevel(Level.ALL);
        logger.setUseParentHandlers(false);
        FileHandler handler=new FileHandler();
        handler.setLevel(Level.FINE);
        logger.addHandler(handler);
        logger.info("123");

    }
}
