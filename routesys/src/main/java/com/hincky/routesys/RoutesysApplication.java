package com.hincky.routesys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.hincky.routesys.dao"})
@SpringBootApplication
public class RoutesysApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoutesysApplication.class, args);
    }

}
