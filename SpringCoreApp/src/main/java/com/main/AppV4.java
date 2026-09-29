package com.main;

import com.config.AppConfig;
import com.dao.MyDao;
import com.mapper.TestMapper;
import com.service.MyServiceV3;
import com.service.MyServiceV4;
import com.utility.TestUtility;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppV4 {
    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);

        MyServiceV4 myServiceV4 =  context.getBean(MyServiceV4.class);
        myServiceV4.test(
                        context.getBean(MyDao.class),
                        context.getBean(TestUtility.class),
                        context.getBean(TestMapper.class));
        myServiceV4.getTime();
    }
}
