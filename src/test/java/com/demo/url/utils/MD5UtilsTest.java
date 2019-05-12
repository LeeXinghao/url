package com.demo.url.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by li.hao on 2019/5/12.
 */
public class MD5UtilsTest {

    @Test
    public void MD5_传入参数为null(){
        String encryptionValue = MD5Utils.MD5(null);
        Assert.assertEquals(null, encryptionValue);
    }

    @Test
    public void MD5_传入参数为空字符串(){
        String encryptionValue = MD5Utils.MD5("");
        Assert.assertEquals(null, encryptionValue);
    }

    @Test
    public void MD5_传入参数正确(){
        String encryptionValue = MD5Utils.MD5("http://www.baidu.com");
        Assert.assertEquals(32, encryptionValue.length());
    }
}