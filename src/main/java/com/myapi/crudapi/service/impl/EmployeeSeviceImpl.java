package com.myapi.crudapi.service.impl;

import com.myapi.crudapi.dto.EmployeeDto;
import com.myapi.crudapi.entity.Employee;
import com.myapi.crudapi.exception.ResourceNotFoundException;
import com.myapi.crudapi.mapper.EmployeeMapper;
import com.myapi.crudapi.repository.EmployeeRepository;
import com.myapi.crudapi.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class EmployeeSeviceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
       Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found: " + employeeId));

       return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
       Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Resource Not Found: " + employeeId)
        );
        employee.setModel(updatedEmployee.getModel());
        employee.setBrand(updatedEmployee.getBrand());
        employee.setYear(updatedEmployee.getYear());
        employee.setPrice(updatedEmployee.getPrice());

       Employee updatedEmployeeObj = employeeRepository.save(employee);

       return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);

    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Resource Not Found: " + employeeId)
        );

        employeeRepository.deleteById(employeeId);
    }
}
