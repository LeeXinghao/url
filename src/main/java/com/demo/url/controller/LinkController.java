package com.demo.url.controller;

import com.demo.url.constant.enums.AccessTypeEnum;
import com.demo.url.service.LinkService;
import com.demo.url.utils.AccessUtils;
import com.demo.url.utils.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by li.hao on 2019/5/11.
 */
@RestController
@RequestMapping(value = "/link")
public class LinkController {

    @Resource
    private LinkService linkService;

    /**
     * 生成短链接
     *
     * @param longUrl
     * @return Caron
     */
    @GetMapping("/api")
    public Object save(String longUrl) {
        AccessUtils.accessCount(AccessTypeEnum.SAVE.getName());
        if (StringUtils.isEmpty(longUrl)) {
            return "参数不合法！";
        }

        if (longUrl.startsWith("http://") || longUrl.startsWith("https://")) {
            return linkService.saveUrl(longUrl);
        } else {
            return "网址必须以http或https开头！";
        }
    }

    /**
     * 链接还原，跳转
     *
     * @param url
     * @return
     */
    @GetMapping("/{url}")
    public String restoreUrl(@PathVariable("url") String url, HttpServletResponse resp) throws IOException {
        AccessUtils.accessCount(AccessTypeEnum.RESTORE.getName());
        if(StringUtils.isEmpty(url)){
            return "参数不合法！";
        }
        String restoreUrl = linkService.restoreUrl(url);
        if (restoreUrl != null && !"".equals(restoreUrl)) {
            resp.sendRedirect(restoreUrl);
        } else {
            return "请求短链接不存在！";
        }
        return "";
    }
}
