package com.demo.url.dao;

import com.demo.url.entity.Link;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by li.hao on 2019/5/11.
 */
@Mapper
public interface LinkMapper {

    void insert(Link link);

    Link selectLinkInfoByLongUrl(String longUrl);

    String selectLongUrlByShortUrl(String shortUrl);
}
