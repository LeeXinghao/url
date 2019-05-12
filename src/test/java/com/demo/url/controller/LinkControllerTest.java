package com.demo.url.controller;

import com.demo.url.service.LinkService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class LinkControllerTest {

    private static MockMvc mvc;

    @InjectMocks
    private LinkController linkController;

    @Mock
    private LinkService linkService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(linkController).build();
    }

    @Test
    public void saveUrl_传入参数为null() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/link/api")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals("参数不合法！", mvcResult.getResponse().getContentAsString().toString());
    }

    @Test
    public void saveUrl_传入参数格式不正确() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/link/api")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .param("longUrl", "www.baidu.com"))
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals("网址必须以http或https开头！", mvcResult.getResponse().getContentAsString().toString());
    }

    @Test
    public void saveUrl_传入参数格式正确() throws Exception {
        BDDMockito.given(linkService.saveUrl("http://baidu.com")).willReturn("q2w2e3");
        MvcResult mvcResult = mvc.perform(get("/link/api")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .param("longUrl", "http://baidu.com"))
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals("q2w2e3", mvcResult.getResponse().getContentAsString().toString());
    }

    @Test
    public void restoreUrl_传入的短链接在数据库中不存在() throws Exception {
        BDDMockito.given(linkService.restoreUrl("q2w2e2")).willReturn(null);
        MvcResult mvcResult = mvc.perform(get("/link/q2w2e2")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals("请求短链接不存在！", mvcResult.getResponse().getContentAsString().toString());
    }

    @Test
    public void restoreUrl_传入的短链接为空() throws Exception {
        mvc.perform(get("/link/")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().is(404));
    }
}