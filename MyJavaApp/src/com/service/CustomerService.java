package com.service;

import com.dao.CustomerDao;
import com.model.Employee;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {
    private CustomerDao customerDao = new CustomerDao();

    public List<Employee> getAllEmployees() {
        return customerDao.getAllEmployees();
    }

    public List<Employee> getEmployeeByDepartment(String dept) throws SQLException {
        return customerDao.getEmployeeByDepartment(dept);
    }

    public List<String> getEmployeeNamesByDepartment(String dept) {
       return customerDao.getEmployeeNamesByDepartment(dept);
    }
}
