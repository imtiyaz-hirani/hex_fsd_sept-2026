package com.main;

import com.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        // From here we need to ensure that our AppConfig class loads
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println("App works");

    }
}
/*
ApplicationContext
        | implements
AnnotationConfigApplicationContext

ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class); -- Polymorphic
* */