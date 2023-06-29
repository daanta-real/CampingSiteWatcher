package com.daanta;

import com.daanta.domain.CampManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        CampManager.initializeCampAll();
    }

}