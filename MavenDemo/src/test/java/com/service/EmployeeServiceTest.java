package com.service;

import com.enums.Branch;
import com.enums.Department;
import com.enums.SortDirection;
import com.exception.InvalidListException;
import com.model.Employee;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class EmployeeServiceTest {

    private EmployeeService employeeService;
    private List<Employee> list;
    private Employee employee1;
    private Employee employee2;
    private Employee employee3;
    private Employee employee4;
    private Employee employee5;
    private Employee employee6;
    private Employee employee7;

    @BeforeEach
    public void init(){
        employeeService = new EmployeeService();
               employee1 = new Employee(2, "Priya Patel", Branch.CHENNAI, "Chennai", Department.ADMIN, LocalDate.of(2019, 7, 1), 62000.0);
               employee2 =  new Employee(3, "John Doe", Branch.NEW_YORK, "New York", Department.FINANCE, LocalDate.of(2022, 1, 15), 110000.0);
               employee3 =  new Employee(4, "Neha Gupta", Branch.MUMBAI, "Pune", Department.DEV, LocalDate.of(2020, 11, 5), 92000.0);
               employee4 =  new Employee(5, "Ramesh Kumar", Branch.CHENNAI, "Vellore", Department.FINANCE, LocalDate.of(2018, 5, 20), 78000.0);
               employee5 = new Employee(6, "Emily Davis", Branch.NEW_YORK, "Brooklyn", Department.ADMIN, LocalDate.of(2023, 2, 10), 65000.0);
               employee6 =  new Employee(7, "Vikram Singh", Branch.MUMBAI, "Mumbai", Department.ADMIN, LocalDate.of(2017, 9, 14), 58000.0);
               employee7 =  new Employee(1, "Aarav Sharma", Branch.MUMBAI, "Mumbai", Department.DEV, LocalDate.of(2021, 3, 12), 85000.0);
        list = Arrays.asList(employee1,employee2,employee3,employee4,employee5,employee6,employee7);
    }

    @Test
    public void sortEmployeeBySalaryTest(){
        assertThrows(InvalidListException.class , ()-> employeeService.sortEmployeeBySalary(null, SortDirection.ASC));
        assertThrows(InvalidListException.class , ()-> employeeService.sortEmployeeBySalary(List.of(), SortDirection.ASC));

        List<Employee> expectedList
                =  List.of(employee6 , employee1, employee5 , employee4, employee7, employee3 , employee2);

        assertEquals(expectedList , employeeService.sortEmployeeBySalary(list , SortDirection.ASC));

        assertEquals(expectedList.reversed() , employeeService.sortEmployeeBySalary(list , SortDirection.DESC));
    }

    @AfterEach
    public void destroy(){
        employeeService = null;
        employee1 = null;
        employee2 = null;
        employee3 = null;
        employee4 = null;
        employee5 = null;
        employee6 = null;
        employee7 = null;
        list = null;
    }
}
