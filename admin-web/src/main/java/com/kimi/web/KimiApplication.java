package com.kimi.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.kimi.system", "com.kimi.common","com.kimi.web"})
public class KimiApplication {
    public static void main(String[] args) {
        SpringApplication.run(KimiApplication.class, args);
        System.out.println("server start success (*^_^*)");
    }
}
