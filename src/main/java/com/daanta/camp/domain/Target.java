package com.daanta.camp.domain;

import com.daanta.camp.utils.Utils;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

// Used for getting the original HTML
@Slf4j
@Getter
@Builder
public abstract class Target {

    private final String url;
    private final Map<String, String> header;
    private final Map<String, String> formBody;
    private final String query;

    @Override
    public String toString() {
        return Utils.getPrettyJson(Map.of(
            "url", this.url,
            "header", this.header,
            "body", this.formBody,
            "query", this.query
        ));

    }

}
