package com.daanta.domain;

import com.daanta.utils.Utils;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Camp {

    private String url;
    private Map<String, String> header;
    private Map<String, String> body;

    @Builder
    public Camp(Map<String, Object> prop) {
        this.url = String.valueOf(prop.get("url"));
        this.header = (Map<String, String>) prop.get("header");
        this.body = (Map<String, String>) prop.get("body");
        log.debug("☞ NEW CAMP: {}", Utils.getPrettyJson(this));
    }

}
