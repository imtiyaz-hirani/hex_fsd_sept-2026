package com.main;

import com.enums.SortDirection;
import com.model.Employee;
import com.service.EmployeeService;

import java.util.List;

public class App {

    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService();
        List<Employee> list =  employeeService.getAllEmployees();
        list.forEach(System.out :: println);

        System.out.println("--Sort Employee by Salary ASC---");
        List<Employee> listSortedBySalaryAsc =  employeeService.sortEmployeeBySalary(list, SortDirection.DESC);
        listSortedBySalaryAsc.forEach(System.out :: println);
    }
}
