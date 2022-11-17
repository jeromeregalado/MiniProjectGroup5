package com.example.MiniProjectGroup5.controller;

import com.example.MiniProjectGroup5.enums.CommunityType;
import com.example.MiniProjectGroup5.exception.RecordNotFoundException;
import com.example.MiniProjectGroup5.model.Employee;
import com.example.MiniProjectGroup5.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    // Adding employee
    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    // Getting all employee
    @GetMapping("/getAll") //(/employees/getAll)
    public ResponseEntity<Page<Employee>> getEmployees(Pageable pageable) {
        Page<Employee> employee = employeeService.findAllEmployees(pageable);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // Getting employee by id
    @GetMapping("/getEmployee/{employeeId}")
    public Employee getEmployeeById(@PathVariable Long employeeId) throws RecordNotFoundException {
        return employeeService.findEmployeeById(employeeId);
    }

    // Getting employee by TYPE
    @GetMapping("/types/{communityType}")
    public Page<Employee> getEmployeeByType(@PathVariable CommunityType communityType, Pageable pageable) throws RecordNotFoundException {
        return employeeService.findByType(communityType, pageable);
    }

    // Update employee by id
    @PutMapping("/updateEmployee/{employeeId}")
    public Employee updateEmployee(
            @PathVariable Long employeeId,
            @RequestBody Employee newEmployee
    ) throws RecordNotFoundException {
        return employeeService.updateEmployee(employeeId, newEmployee);
    }

    // Delete employee by id
    @DeleteMapping("/deleteEmployee/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId) throws RecordNotFoundException {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("Employee Delete Complete",HttpStatus.OK);
    }
}
