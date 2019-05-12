package com.demo.url.service;

import com.demo.url.dao.LinkMapper;
import com.demo.url.entity.Link;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by li.hao on 2019/5/12.
 */
@SpringBootTest()
@RunWith(SpringRunner.class)
public class LinkServiceImplTest {

    @Value("${short.url.length}")
    private int shortUrlLength;

    @Resource
    private LinkService linkService;

    @Resource
    private LinkServiceImpl linkServiceImpl;

    @MockBean
    private LinkMapper linkMapper;

    @Test
    public void saveUrl_传入参数为null() {
        String shortUrl = linkService.saveUrl(null);
        Assert.assertEquals(null, shortUrl);
    }

    @Test
    public void saveUrl_传入参数为空字符串() {
        String shortUrl = linkService.saveUrl("");
        Assert.assertEquals(null, shortUrl);
    }

    @Test
    public void saveUrl_传入长链接在数据库中不存在() {

        String longUrl = "http:www.baidu.com";
        BDDMockito.given(linkMapper.selectLinkInfoByLongUrl(longUrl)).willReturn(null);

        String shortUrl = linkService.saveUrl(longUrl);
        Assert.assertEquals(shortUrlLength, shortUrl.length());
    }

    @Test
    public void saveUrl_传入长链接在数据库中存在() {
        String longUrl = "http:www.baidu.com";

        Link link = new Link();
        link.setLongUrl(longUrl);
        link.setShortUrl("q1w2e3");
        BDDMockito.given(linkMapper.selectLinkInfoByLongUrl(longUrl)).willReturn(link);

        String shortUrl = linkService.saveUrl(longUrl);
        Assert.assertEquals(6, shortUrl.length());
    }

    @Test
    public void restoreUrl_传入参数为null() {
        String longUrl = linkService.restoreUrl(null);
        Assert.assertEquals(null, longUrl);
    }

    @Test
    public void restoreUrl_传入参数为空字符串() {
        String longUrl = linkService.restoreUrl("");
        Assert.assertEquals(null, longUrl);
    }

    @Test
    public void restoreUrl_传入参数正确() {
        BDDMockito.given(linkMapper.selectLongUrlByShortUrl("q1w2e3")).willReturn("http://www.baidu.com");
        String longUrl = linkService.restoreUrl("q1w2e3");
        Assert.assertEquals("http://www.baidu.com", longUrl);
    }

    @Test
    public void gererateShortUrl_传入参数为空() {
        String shortUrl = linkServiceImpl.gererateShortUrl(null);
        Assert.assertEquals(null, shortUrl);
    }

    @Test
    public void gererateShortUrl_传入参数为字符串() {
        String shortUrl = linkServiceImpl.gererateShortUrl("");
        Assert.assertEquals(null, shortUrl);
    }

    @Test
    public void gererateShortUrl_传入参数正确() {
        String longUrl = linkServiceImpl.gererateShortUrl("http://www.baidu.com");
        Assert.assertEquals(shortUrlLength, longUrl.length());
    }
}