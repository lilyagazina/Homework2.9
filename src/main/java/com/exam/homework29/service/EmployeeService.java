package com.exam.homework29.service;

import com.exam.homework29.exception.DoesNotContainText;
import com.exam.homework29.exception.EmployeeAlreadyAddedException;
import com.exam.homework29.exception.EmployeeNotFoundException;
import com.exam.homework29.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.StringUtils.isEmpty;

@Service
public class EmployeeService {
    private final Map<String, Employee> employees;


    public EmployeeService() {
        this.employees = new HashMap<>();
    }

    public Employee add(String firstName, String lastName, int salary, int department) {
        checkIfTextContains(firstName, lastName);
        checkForCharactersInString(firstName, lastName);
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName, int salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employees.containsKey(employee.getFullName())) {
            employees.remove(employee.getFullName());
            return employee;
        }
        throw new EmployeeNotFoundException();
    }


    public Employee find(String firstName, String lastName, int salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employees.containsKey(employee.getFullName())) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }


    public List<Employee> findAll() {
        List<Employee> employeeList = new ArrayList<>(employees.values());
        return employeeList;
    }

    private void checkIfTextContains(String firstName, String lastName) {
        //проверяет, содержит ли строка текст
        if (isEmpty(firstName) && isEmpty(lastName)) {
            throw new DoesNotContainText();
        }
    }

    private void checkForCharactersInString(String firstName, String lastName) {
        //проверяет символы в строке и записываются с большой буквы
       /* firstName = capitalize(firstName.toLowerCase());
        lastName = capitalize(lastName.toLowerCase());*/
        if (!(firstName.matches("[a-zA-Z]+") && lastName.matches("[a-zA-Z]+"))) {
            throw new DoesNotContainText();
        }
    }
}





