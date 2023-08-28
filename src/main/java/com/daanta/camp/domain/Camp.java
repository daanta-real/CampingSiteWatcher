package com.daanta.camp.domain;

import lombok.NonNull;

public class Camp {

    @NonNull
    private String name;
    @NonNull
    private String type;

    private String header;
    private String body;

    public Camp(
            @NonNull String name,
            @NonNull String type,
            String header,
            String body
    ) {
        this.name = name;
        this.type = type;
        this.header = header;
        this.body = body;
    }

}
