package com.daanta.camp;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@MapperScan("com.daanta.camp.dao")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}