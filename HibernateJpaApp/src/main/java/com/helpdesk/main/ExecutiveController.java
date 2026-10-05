package com.helpdesk.main;

import com.helpdesk.config.AppConfig;
import com.helpdesk.enums.JobTitle;
import com.helpdesk.model.Executive;
import com.helpdesk.service.ExecutiveService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ExecutiveController {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ExecutiveService executiveService =  context.getBean(ExecutiveService.class);
        /* Take this from user either thru react app or console using scanner or just prepare the input */
        int managerId = 2;
        Executive executive = new Executive();
        executive.setName("draco malfoy");
        executive.setJobTitle(JobTitle.SECOND_LINE_SUPPORT);
        String username = "draco@gmail.com";
        String password = "draco@123";

        executiveService.insertExecutive(managerId, executive , username, password);
        System.out.println("record inserted...");
    }
}


/*
Spring's Context
--------
 ExecutiveService
 ExecutiveRepository
 EntityManager (persistence)
 UserRepository
* */