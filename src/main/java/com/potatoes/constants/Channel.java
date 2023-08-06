package com.potatoes.constants;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum Channel {

    PC_WEB("W", StringUtils.EMPTY),
    APP("A",  "피로회복 앱");

    private final String code;
    private final String name;

    Channel(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
