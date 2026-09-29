package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
@ComponentScan(basePackages = "com.*")
public class AppConfig {

    @Bean
    public Clock configureClock(){
        return Clock.systemUTC();
    }
}
