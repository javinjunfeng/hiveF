package com.javinjunfeng.hivef;

/**
 * Author javinjunfeng
 * Date 2018/2/22
 * Github https://github.com/javinjunfeng
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

/**
 * 解析sql文件中的SQL语句，并返回解析后的语句
 */
public class Utils {
    public static final String BEGIN = "${";
    public static final String END = "}";

    public static String getSql(File file) throws Exception {
        BufferedReader bf = new BufferedReader(new FileReader(file));
        StringBuffer sqlBuffer = new StringBuffer();
        String temp = null;
        while ((temp = bf.readLine()) != null) {
            String tmp = temp.trim();
            if (tmp.length() == 0 || tmp.startsWith("#") || tmp.startsWith("--")) {
                continue;
            }
            sqlBuffer.append(tmp + " ");
        }
        bf.close();
        return sqlBuffer.toString();

    }

    /**
     * 把sql里的参数引用，替换为map里的value
     *
     * @param sql
     * @param map
     */
    public static String parse(String sql, Map<String, String> map) {
        int begin = sql.indexOf(BEGIN);
        while (begin != -1) {
            String suffix = sql.substring(begin + BEGIN.length());
            int end = begin + BEGIN.length() + suffix.indexOf(END);
            String key = sql.substring(begin + BEGIN.length(), end).trim();
            if (map != null && map.get(key) != null) {
                sql = sql.substring(0, begin) + map.get(key) + sql.substring(end + 1, sql.length());
            } else {
                throw new RuntimeException("Invalid Expression.....");
            }
            begin = sql.indexOf(BEGIN);
        }
        return sql;
    }
}
