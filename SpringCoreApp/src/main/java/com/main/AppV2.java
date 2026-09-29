package com.main;

import com.config.AppConfig;
import com.service.MyServiceV2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppV2 {
    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);

        MyServiceV2 myServiceV2 =  context.getBean(MyServiceV2.class);
        myServiceV2.test();

    }
}
