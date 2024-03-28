package com.myapi.crudapi.mapper;

import com.myapi.crudapi.dto.EmployeeDto;
import com.myapi.crudapi.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto(Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getModel(),
                employee.getBrand(),
                employee.getPrice(),
                employee.getYear()
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto){
        return new Employee(
                employeeDto.getId(),
                employeeDto.getModel(),
                employeeDto.getBrand(),
                employeeDto.getPrice(),
                employeeDto.getYear()
        );
    }
}
