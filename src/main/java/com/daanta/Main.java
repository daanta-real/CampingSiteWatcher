package com.daanta;

import com.daanta.conf.PropsCommon;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Slf4j
public class Main {

    public static Properties p;

    private static void init() {

        // LOAD MAIN PROPS
        p = PropsCommon.getInstance();

    }

    public static void main(String[] args) {
        init();
    }

}