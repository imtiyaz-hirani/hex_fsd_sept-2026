package com.service;

import com.dao.EmployeeDao;
import com.enums.SortDirection;
import com.model.Employee;

import java.util.List;

public class EmployeeService {
    private EmployeeDao employeeDao = new EmployeeDao();

    public List<Employee> getAllEmployees() {
        return employeeDao.getALlEmployees();

    }

    public List<Employee> sortEmployeeBySalary(List<Employee> list, SortDirection direction) {

      if(direction.equals(SortDirection.ASC))
          list.sort((e1, e2) -> (int) (e1.getSalary() - e2.getSalary())); //[50,80]
       else
          list.sort((e1,e2)-> (int)(e2.getSalary() - e1.getSalary()));

      return list;
    }



}
// List: [ml1, ml 2, ml3, ml4 ]