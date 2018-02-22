package com.javinjunfeng.hivef;

/**
 * Author javinjunfeng
 * Date 2018/2/22
 * Github https://github.com/javinjunfeng
 */

import java.io.File;

/**
 * hiveF的客户端
 *
 * eg : hiveF /opt/bin/jar/test.sql -yesterday 20151015 -today 20151016 -hour 15
 *
 * 第一个参数为sql文件的全路径，第二、三参数等为sql语句中的参数，且格式为: -key value。
 *
 */
public class Main {
    public static void main(String[] args) throws Exception {
        if (args == null || args.length == 0) {
            System.out.println("请输入参数，第一个参数为sql文件的全路径，第二、三等为sql语句中的参数: -key value，例如： hiveF /opt/bin/jar/test.sql -yesterday 20151015 -today 20151016 -hour 15 ");
            return;
        }

        ParseArgs parse = new ParseArgs(args);
        String sql = Utils.getSql(new File(args[0]));
        System.out.println(Utils.parse(sql, parse.getMap()));
    }
}
