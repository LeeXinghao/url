package com.demo.url.utils;

/**
 * 字符串处理公共方法
 * <p>
 * Created by li.hao on 2019/5/11.
 */
public class StringUtils {

    private StringUtils() {
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
