package com.service;

import com.exception.EmptyListException;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DemoServiceTest {

    DemoService demoService;  // Ref
    List<Double> listMarks1;
    List<Double> listMarks2;
    List<Double> listMarks3;
    List<Double> listMarks4;
    List<Double> listMarks5;
    List<Double> listMarks6;
    // This is called before each test starts
    @BeforeEach
    public void init(){
        demoService = new DemoService();
        listMarks1 = List.of(78d,58d,86d,75d); // valid
        listMarks2 = List.of(78d,105d,86d,75d);// invalid - >100
        listMarks3 = List.of(78d,58d,86d,-3d);// invalid - <0
        listMarks4 = List.of(); // invalid - empty
        listMarks5 = List.of(78d,78d,86d,75d); // valid
        listMarks6 = List.of(68d,58d,56d,45d); // valid
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

    @Test
    public void computeGradeTestFunctional(){
        assertNotNull(listMarks5);
        assertEquals("A", demoService.computeGrade(listMarks5));
        assertNotNull(listMarks1);
        assertEquals("B", demoService.computeGrade(listMarks1));
        assertNotNull(listMarks6);
        assertEquals("C", demoService.computeGrade(listMarks6));
        assertNotNull(listMarks6);
        assertNotEquals("A", demoService.computeGrade(listMarks6));
    }

    @Test
    public void computeGradeTestValidation(){
        // Null check - throwing Exception
        assertEquals("List cannot be null" ,
                    assertThrows(NullPointerException.class ,
                                    ()-> demoService.computeGrade(null))
                                    .getMessage() );

        // Empty check - throwing EmptyListException
        assertEquals("List has no elements" ,
                    assertThrows(EmptyListException.class ,
                            ()-> demoService.computeGrade(listMarks4))
                            .getMessage());

        // Incorrect Marks entry check - InvalidInputException


    }
    // This will be called after each test has completed
    @AfterEach
    public void destroy(){
        demoService = null;
    }
}
