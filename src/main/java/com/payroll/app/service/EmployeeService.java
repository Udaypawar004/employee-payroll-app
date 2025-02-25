package com.payroll.app.service;

import com.payroll.app.dto.EmployeeDTO;
import com.payroll.app.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private List<Employee> employees = new ArrayList<>();

    public Employee getEmployee(Long id) {
        Iterator<Employee> itr = employees.iterator();
        while(itr.hasNext()) {
            if (Objects.equals(itr.next().getId(), id)) {
                System.out.println(employees);
                return itr.next();
            }
        }
        return null;
    }

    public Employee addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        employees.add(employee);
        return employee;
    }

    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Iterator<Employee> itr = employees.iterator();
        while(itr.hasNext()) {
            if (Objects.equals(itr.next().getId(), id)) {
                itr.next().setName(employeeDTO.getName());
                itr.next().setSalary(employeeDTO.getSalary());
            }
        }
        return null;
    }

    public boolean deleteEmployee(Long id) {
        Iterator<Employee> itr = employees.iterator();
        while(itr.hasNext()) {
            if (Objects.equals(itr.next().getId(), id)) {
                employees.remove(itr.next());
                return true;
            }
        }
        return false;
    }
}
