package com.payroll.app.service;

import com.payroll.app.dto.EmployeeDTO;
import com.payroll.app.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class EmployeeService {
    private List<Employee> employees = new ArrayList<>();
    private ModelMapper modelMapper = new ModelMapper();

    public EmployeeDTO getEmployee(Long id) {
        Iterator<Employee> itr = employees.iterator();
        while(itr.hasNext()) {
            if (Objects.equals(itr.next().getId(), id)) {
                log.info("Successfully got employee by id.");
                return modelMapper.map(itr.next(), EmployeeDTO.class);
            }
        }
        log.error("Error while getting employee by there id.");
        return null;
    }

    public List<EmployeeDTO> getAllEmployees() {
        if (employees == null) {
            log.error("No employee are there.");
            return null;
        }
        List<EmployeeDTO> emp = new ArrayList<>();

        for (Employee em: employees) {
            emp.add(modelMapper.map(em, EmployeeDTO.class));
        }
        log.info("Successfully getting all employee.");
        return emp;
    }

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        employees.add(employee);
        log.info("Employee added successfully.");
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Iterator<Employee> itr = employees.iterator();
        while(itr.hasNext()) {
            Employee emp = itr.next();
            if (Objects.equals(emp.getId(), id)) {
                emp.setName(employeeDTO.getName());
                emp.setSalary(employeeDTO.getSalary());
                log.info("Employee update successfully.");
                return modelMapper.map(emp, EmployeeDTO.class);
            }
        }
        return null;
    }

    public boolean deleteEmployee(Long id) {
        Iterator<Employee> itr = employees.iterator();
        while(itr.hasNext()) {
            Employee emp = itr.next();
            if (Objects.equals(emp.getId(), id)) {
                employees.remove(emp);
                log.info("Employee deleted successfully.");
                return true;
            }
        }
        return false;
    }
}
