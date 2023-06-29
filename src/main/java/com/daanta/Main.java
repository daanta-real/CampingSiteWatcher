package com.daanta;

import com.daanta.conf.PropsCommon;
import com.daanta.conf.PropsCamp;
import com.daanta.domain.Camp;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Properties;

@Slf4j
public class Main {

    public static Properties p;

    private static void init() {

        // LOAD MAIN PROPS
        p = PropsCommon.getInstance();

        // CAMPING SITE LOADING - Nanji island
        Map<String, Object> pNanji = PropsCamp.loadCampingSiteProps("nanji");
        Camp camp = Camp.builder().prop(pNanji).build();

    }

    public static void main(String[] args) {
        init();
    }

}