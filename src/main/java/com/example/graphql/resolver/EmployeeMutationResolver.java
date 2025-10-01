package com.example.graphql.resolver;

import com.example.graphql.entity.Employee;
import com.example.graphql.repository.EmployeeRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeMutationResolver {
    private final EmployeeRepository employeeRepository;

    public EmployeeMutationResolver(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @MutationMapping
    public Employee addEmployee(@Argument String name, @Argument String department, @Argument Double salary) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setDepartment(department);
        employee.setSalary(salary);
        return employeeRepository.save(employee);
    }

    @MutationMapping
    public Employee updateEmployee(@Argument Long id, @Argument String name, @Argument String department, @Argument Double salary) {
        return employeeRepository.findById(id).map(e -> {
            if (name != null) e.setName(name);
            if (department != null) e.setDepartment(department);
            if (salary != null) e.setSalary(salary);
            return employeeRepository.save(e);
        }).orElse(null);
    }

    @MutationMapping
    public Boolean deleteEmployee(@Argument Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
