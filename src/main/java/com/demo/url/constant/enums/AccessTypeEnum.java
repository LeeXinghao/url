package com.demo.url.constant.enums;

import lombok.Getter;

/**
 * 访问类型枚举值
 * <p>
 * Created by li.hao on 2019/5/12.
 */
public enum AccessTypeEnum {
    SAVE("save", "生成短链接"),
    RESTORE("restore","恢复长链接");

    @Getter
    private String name;

    @Getter
    private String desc;

    AccessTypeEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}
