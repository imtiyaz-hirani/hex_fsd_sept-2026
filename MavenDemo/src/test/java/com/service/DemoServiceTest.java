package com.service;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DemoServiceTest {

    DemoService demoService;  // Ref

    // This is called before each test starts
    @BeforeEach
    public void init(){
        System.out.println("inside init....");
        demoService = new DemoService();
    }

    // The actual test method
    @Test
    public void sumTest(){
        System.out.println("inside test....");
        // Use case: both +Ve inputs
        int actualOutput = demoService.sum(2,3);
        int expectedOutput = 5;
        assertEquals(expectedOutput,actualOutput);

        // Use case: one -ve & one +ve inputs
        actualOutput = demoService.sum(-2,3);
        expectedOutput = 1;
        assertEquals(expectedOutput,actualOutput);

        // Use case: both -ve inputs
        actualOutput = demoService.sum(-2,-3);
        expectedOutput = -5;
        assertEquals(expectedOutput,actualOutput);

        // Use case: going wrong.. then fixing
        actualOutput = demoService.sum(5,4);
        expectedOutput = 10;
        assertNotEquals(expectedOutput,actualOutput);
    }

    // This will be called after each test has completed
    @AfterEach
    public void destroy(){
        System.out.println("inside destroy....");
        demoService = null;
    }
}
