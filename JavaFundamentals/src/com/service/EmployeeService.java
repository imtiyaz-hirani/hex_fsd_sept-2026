package com.service;

import com.dao.EmployeeDao;
import com.dto.EmpDeptStatDto;
import com.dto.EmployeeRespDto;
import com.dto.EmployeeSalaryStatDto;
import com.enums.Branch;
import com.enums.Department;
import com.enums.SortDirection;
import com.mapper.EmployeeMapper;
import com.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeService {
    private EmployeeDao employeeDao = new EmployeeDao();

    public List<Employee> getAllEmployees() {
        return employeeDao.getALlEmployees();

    }

    public List<Employee> sortEmployeeBySalary(List<Employee> list, SortDirection direction) {

      if(direction.equals(SortDirection.ASC))
          list.sort((e1, e2) -> (int) (e1.getSalary() - e2.getSalary())); //[80,50]
       else
          list.sort((e1,e2)-> (int)(e2.getSalary() - e1.getSalary()));

      return list;
    }


    public List<Employee> filterEmployeeByDepartment(List<Employee> list, Department department) {
           return  list
                    .stream()
                    .filter(e->e.getDepartment().equals(department))
                    .toList();
    }

    public List<Employee> filterEmployeeByBranch(List<Employee> list, Branch branch) {

            return  list
                    .stream()
                    .filter(e-> e.getBranch().equals(branch))
                    .toList();
    }


    public List<String> getEmployeeNames(List<Employee> list) {
       return   list
                    .stream()
                    .map(Employee::getName)
                    .toList();
    }

    public List<EmployeeRespDto> getEmployeeInfo(List<Employee> list) {

            return list.stream()
                    .map(EmployeeMapper::mapModelToDto)
                    .toList();

    }

    public List<EmpDeptStatDto> getEmpCountForEachDepartment(List<Employee> list) {

               return list
                    .stream()
                    .collect(
                            Collectors.groupingBy(Employee :: getDepartment,
                                                  Collectors .counting()))
                       .entrySet()
                       .stream()
                       .map(entry-> new EmpDeptStatDto(entry.getKey(), entry.getValue()))
                       .toList();
    }

    public List<EmployeeSalaryStatDto> getSalaryOfEachEmpByBranch(List<Employee> list) {
        return list
                .stream()
                .collect(Collectors.groupingBy(
                        Employee :: getBranch,
                        Collectors.summingDouble(Employee :: getSalary)
                ))
                .entrySet()
                .stream()
                .map(entry -> new EmployeeSalaryStatDto(entry.getKey(), entry.getValue()))
                .toList();
    }

    public double computeTotalSalary(List<EmployeeSalaryStatDto> listBranchStatDto) {
        return ((Double) listBranchStatDto
                .stream()
                .mapToDouble(EmployeeSalaryStatDto::salary)
                .sum());

    }
}