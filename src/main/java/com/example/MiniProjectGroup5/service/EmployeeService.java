package com.example.MiniProjectGroup5.service;

import com.example.MiniProjectGroup5.enums.CommunityType;
import com.example.MiniProjectGroup5.exception.RecordNotFoundException;
import com.example.MiniProjectGroup5.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    Page<Employee> findAllEmployees(Pageable pageable);

    Employee findEmployeeById(Long employeeId) throws RecordNotFoundException;

    Employee updateEmployee(Long employeeId, Employee newEmployee) throws RecordNotFoundException;

    public Page<Employee> findByType(CommunityType communityType, Pageable pageable) throws ResponseStatusException;

    void deleteEmployee(Long employeeId) throws RecordNotFoundException;
}
