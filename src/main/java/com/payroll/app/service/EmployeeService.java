package com.payroll.app.service;

import com.payroll.app.dto.EmployeeDTO;
import com.payroll.app.model.Employee;
import com.payroll.app.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employees;
    public EmployeeService(EmployeeRepository employees) {
        this.employees = employees;
    }

    private final ModelMapper modelMapper = new ModelMapper();


    public EmployeeDTO getEmployee(Long id) {
        Optional<Employee> employee = employees.findById(id);
        if(employee.isPresent()) {
            log.info("Successfully got employee by id.");
            return modelMapper.map(employee, EmployeeDTO.class);
        }
        log.error("Error while getting employee by there id.");
        return null;
    }

    public List<EmployeeDTO> getAllEmployees() {
        if (employees == null) {
            log.error("No employee are there.");
            return Collections.emptyList();
        }
        List<Employee> emp = employees.findAll();
        List<EmployeeDTO> em = new ArrayList<>();

        for (Employee e: emp) {
            em.add(modelMapper.map(e, EmployeeDTO.class));
        }
        log.info("Successfully getting all employee.");
        return em;
    }

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        employees.save(employee);
        log.info("Employee added successfully.");
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> employeeOptional = employees.findById(id);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();

            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            Employee updatedEmployee = employees.save(employee);

            log.info("Employee updated successfully.");
            return modelMapper.map(updatedEmployee, EmployeeDTO.class)  ;
        }

        log.error("Error while updating employee");
        throw new EntityNotFoundException("Employee with ID " + id + " not found");
    }

    public boolean deleteEmployee(Long id) {
        if (employees.existsById(id)) {
            employees.deleteById(id);
            log.info("Employee deleted successfully.");
            return true;
        }
        log.warn("Employee with ID {} not found.", id);
        return false;
    }
}
