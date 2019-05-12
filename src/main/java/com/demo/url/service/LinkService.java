package com.demo.url.service;

/**
 * Created by li.hao on 2019/5/11.
 */
public interface LinkService {


    /**
     * 保存链接
     *
     * @param longUrl
     * @return
     */
    String saveUrl(String longUrl);

    /**
     * 还原链接
     *
     * @param url
     * @return
     */
    String restoreUrl(String url);
}
