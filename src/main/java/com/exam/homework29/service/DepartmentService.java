package com.exam.homework29.service;

import com.exam.homework29.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee maxSalary(int department) {
        return employeeService.findAll().stream()
                .filter(emp -> emp.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    public Employee minSalary(int department) {
        return  employeeService.findAll().stream()
                .filter(emp -> emp.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    public List<Employee> returnAll(int department) {
        return employeeService.findAll().stream()
                .filter(emp -> emp.getDepartment() == department)
                .collect(Collectors.toList());
    }
    public void chanceDepartment(Employee employee, int newDepartment) {
        employeeService.findAll().stream()
                .filter(value -> Objects.equals(employee, value))
                .findFirst()
                .ifPresent(value -> value.setDepartment(newDepartment));
    }

    public Map<Integer, List<Employee>> findAll() {
        return employeeService.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

    }

}

