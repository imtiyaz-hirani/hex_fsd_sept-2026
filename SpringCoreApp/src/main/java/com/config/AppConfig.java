package com.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    static{
        System.out.println("AppConfig loads...");
    }
}
