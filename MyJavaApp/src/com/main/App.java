package com.main;

import com.model.Employee;
import com.service.CustomerService;

import java.sql.SQLException;
import java.util.List;

public class App {

    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        List<Employee> list =  customerService.getAllEmployees();
        list.forEach(System.out :: println);

        System.out.println("------------Emp by Dept----------");
        String dept = "DEV";
        try {
            list = customerService.getEmployeeByDepartment(dept);
            list.forEach(System.out :: println);
            System.out.println("------Number of Employee by Dept-----");
            int count = customerService.getEmpCountByDept(dept);
            System.out.println("Number of Emp: " + count);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    /*
        List<String> empNames = customerService.getEmployeeNamesByDepartment(dept);
        list.forEach(System.out :: println);
    */

    }
}
