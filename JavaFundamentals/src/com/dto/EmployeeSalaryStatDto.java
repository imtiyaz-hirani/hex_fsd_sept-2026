package com.dto;

import com.enums.Branch;

public record EmployeeSalaryStatDto(
        Branch branch,
        double salary
) {
}
