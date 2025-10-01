package com.example.graphql.resolver;

import com.example.graphql.entity.Employee;
import com.example.graphql.repository.EmployeeRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EmployeeQueryResolver {
    private final EmployeeRepository employeeRepository;

    public EmployeeQueryResolver(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @QueryMapping
    public List<Employee> employees() {
        return employeeRepository.findAll();
    }

    @QueryMapping
    public Employee employeeById(@Argument Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Employee> employeesByDepartment(@Argument String department) {
        return employeeRepository.findByDepartment(department);
    }
}
