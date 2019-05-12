package com.demo.url.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 链接实体
 * <p>
 * Created by li.hao on 2019/5/11.
 */
@Setter
@Getter
public class Link {

    private Integer id;

    private String shortUrl;

    private String longUrl;
}
