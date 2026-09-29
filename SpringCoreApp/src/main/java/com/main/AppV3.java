package com.main;

import com.config.AppConfig;
import com.service.MyServiceV2;
import com.service.MyServiceV3;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppV3 {
    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);

        MyServiceV3 myServiceV3 =  context.getBean(MyServiceV3.class);
        myServiceV3.test();
    }
}
