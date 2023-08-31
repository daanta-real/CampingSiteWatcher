package com.daanta.camp.domain;

import com.daanta.camp.utils.Utils;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Map;



// Used for handling one Site

@Getter
@Setter
@SuperBuilder
public abstract class Site {

    private String url;
    private Map<String, String> header;
    private Map<String, String> formBody;
    private String query;
    private String latestValue;

    @Override
    public String toString() {
        return Utils.getPrettyJson(Map.of(
                "url", this.url,
                "header", this.header,
                "body", this.formBody,
                "query", this.query
        ));
    }

    public abstract void get();
    public abstract void getNewValue();

}
