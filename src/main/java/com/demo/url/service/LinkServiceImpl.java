package com.demo.url.service;

import com.demo.url.dao.LinkMapper;
import com.demo.url.entity.Link;
import com.demo.url.utils.MD5Utils;
import com.demo.url.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by li.hao on 2019/5/11.
 */
@Service
public class LinkServiceImpl implements LinkService {

    @Value("${short.url.length}")
    private int shortUrlLength;

    @Resource
    private LinkMapper linkMapper;

    /**
     * 转化url
     * 1.查询当前长链接在数据库中是否存在，如果存在直接返回其对应的短链接;
     * 2.如果长链接在数据库中不存在，就将长链接转化为短链接，记录入库并返回转换过后的短链接。
     *
     * @param longUrl
     * @return
     */
    @Override
    public String saveUrl(String longUrl) {
        if(StringUtils.isEmpty(longUrl)){
            return null;
        }
        String shortUrl = "";
        Link linkInfo = linkMapper.selectLinkInfoByLongUrl(longUrl);
        if (linkInfo == null) {
            Link link = new Link();
            link.setShortUrl(shortUrl += gererateShortUrl(longUrl));
            link.setLongUrl(longUrl);

            linkMapper.insert(link);
        } else {
            shortUrl = linkInfo.getShortUrl();
        }
        return shortUrl;
    }

    @Override
    public String restoreUrl(String url) {
        if(StringUtils.isEmpty(url)){
            return null;
        }
        return linkMapper.selectLongUrlByShortUrl(url);
    }

    /**
     * 将长链接转换为短链接
     *
     * @param longUrl
     * @return
     */
    public String gererateShortUrl(String longUrl) {
        if(StringUtils.isEmpty(longUrl)){
            return null;
        }
        // 可以自定义生成 MD5 加密字符串前的混合 KEY
        String customKey = "customPrefix";
        // 要使用生成 URL 的字符
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"
        };
        // 对传入网址进行 MD5 加密
        String urlMD5EncryptResult = MD5Utils.MD5(customKey + longUrl);
        // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算，
        String sTempSubString = urlMD5EncryptResult.substring(2 * 8, 2 * 8 + 8);    //固定取第三组(一共四组，也可以取其他组)
        // 使用 long 型来转换，处理越界问题。lHexLong长度为30
        long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);

        String outChars = "";
        for (int j = 0; j < shortUrlLength; j++) {
            // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
            long index = 0x0000003D & lHexLong;
            // 把取得的字符相加
            outChars += chars[(int) index];
            // 每次循环按位右移 5 位
            lHexLong = lHexLong >> (30/shortUrlLength);
        }
        return outChars;
    }
}
