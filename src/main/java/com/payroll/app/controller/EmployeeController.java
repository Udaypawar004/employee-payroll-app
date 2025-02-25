package com.payroll.app.controller;

import com.payroll.app.dto.EmployeeDTO;
import com.payroll.app.model.Employee;
import com.payroll.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    // GET all employees
    @GetMapping
    public ResponseEntity<String> getAllEmployees() {
        return new ResponseEntity<>("Hello World - Get All Employees", HttpStatus.OK);
    }

    // GET employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployee(id);
        if(employee == null) {
            new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // POST - Create new employee
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.addEmployee(employeeDTO);
        if(employee == null) {
            new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    // PUT - Update existing employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeData) {
        Employee employee = employeeService.updateEmployee(id, employeeData);
        if(employee == null) {
            new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // DELETE - Delete employee
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        boolean isSuccess = employeeService.deleteEmployee(id);

        if(isSuccess) {
            return "Deleted successfully";
        }
        return "Can't delete";
    }
}
