package com.exam.homework29.controller;

import com.exam.homework29.model.Employee;
import com.exam.homework29.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("salary") int salary,
                                @RequestParam("departmentId") int department) {
        return employeeService.add(firstName, lastName, salary, department);
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName,
                                   @RequestParam("salary") int salary,
                                   @RequestParam("departmentId") int department) {
        return employeeService.remove(firstName, lastName, salary, department);
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("salary") int salary,
                                 @RequestParam("departmentId") int department) {
        return employeeService.find(firstName, lastName, salary, department);
    }

    @GetMapping
    public Collection<Employee> findAll() {
        return employeeService.findAll();
    }
}

