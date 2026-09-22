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
        String dept = "IT";
        try {
            list = customerService.getEmployeeByDepartment(dept);
            list.forEach(System.out :: println);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    /*
        List<String> empNames = customerService.getEmployeeNamesByDepartment(dept);
        list.forEach(System.out :: println);
    */

    }
}
