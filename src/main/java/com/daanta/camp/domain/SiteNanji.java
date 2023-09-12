package com.daanta.camp.domain;

import lombok.Builder;
import lombok.experimental.SuperBuilder;



// Nanji campsite

@SuperBuilder
public class SiteNanji extends Site {

    @Builder.Default
    private String type = "nanji";

    @Override
    public void get() {
        // 구현
    }

    @Override
    public void getNewValue() {
        // 구현
    }
}
