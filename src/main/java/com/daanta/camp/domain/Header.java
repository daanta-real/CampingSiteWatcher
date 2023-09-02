package com.daanta.camp.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Header {
    private String id;
    private String siteId;
    private String key;
    private String val;
}
