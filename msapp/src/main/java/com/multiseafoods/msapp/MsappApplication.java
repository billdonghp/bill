package com.multiseafoods.msapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.multiseafoods.msapp.mapper")
@SpringBootApplication
public class MsappApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsappApplication.class, args);
    }

}
