package com.daanta.domain;

import com.daanta.conf.Camps;
import com.daanta.utils.Utils;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

// Common values reardless of camp type

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CampBase {

    private String url;
    private Map<String, String> header;
    private Map<String, String> body;

    @Builder
    public CampBase(String campName) {
        Map<String, Object> campValues = Camps.initOne(campName);
        this.url = String.valueOf(campValues.get("url"));
        this.header = (Map<String, String>) campValues.get("header");
        this.body = (Map<String, String>) campValues.get("body");
        log.debug("☞ NEW CAMP: {}", Utils.getPrettyJson(this));
    }

}
