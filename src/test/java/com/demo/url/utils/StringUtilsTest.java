package com.demo.url.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by li.hao on 2019/5/12.
 */
public class StringUtilsTest {

    @Test
    public void isEmpty_传入参数为null() {
        boolean isEnpty = StringUtils.isEmpty(null);
        Assert.assertEquals(true, isEnpty);
    }

    @Test
    public void isEmpty_传入参数为空字符串() {
        boolean isEnpty = StringUtils.isEmpty("");
        Assert.assertEquals(true, isEnpty);
    }

    @Test
    public void isEmpty_传入参数正确() {
        boolean isEnpty = StringUtils.isEmpty("string");
        Assert.assertEquals(false, isEnpty);
    }
}