package com.payroll.app.controller;

import com.payroll.app.dto.EmployeeDTO;
import com.payroll.app.exceptions.EmployeePayrollException;
import com.payroll.app.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(value="*")
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    // GET all employees
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        if(employees == null) {
            log.error("Employee data are not there");
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
        log.info("Getting all employees successfully.");
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // GET employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employee = employeeService.getEmployee(id);
        if(employee == null) {
            log.error("Error while getting the employee by there id.");
            throw new EmployeePayrollException("ID Not found. while getting");
        }
        log.info("Successfully got employee.");
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // POST - Create new employee
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO employee = employeeService.addEmployee(employeeDTO);
        if(employee == null) {
            log.error("Error while adding employee.");
            throw new EmployeePayrollException("Error while Creating Employee");
        }
        log.info("Employee added successfully.");
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    // PUT - Update existing employee
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@Valid @PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeData) {
        EmployeeDTO employee = employeeService.updateEmployee(id, employeeData);
        if(employee == null) {
            log.error("Error while updating the employee.");
            throw new EmployeePayrollException("Error while updating employee");
        }
        log.info("Employee updated successfully.");
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // DELETE - Delete employee
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        boolean isSuccess = employeeService.deleteEmployee(id);

        if(isSuccess) {
            log.info("Employee deleted successfully.");
            return "Deleted successfully";
        }
        log.error("Error while deleting the employee.");
        throw new EmployeePayrollException("Error while deleting employee.");
    }
}
