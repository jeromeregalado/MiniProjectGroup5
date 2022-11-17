package com.example.MiniProjectGroup5.service;

import com.example.MiniProjectGroup5.enums.CommunityType;
import com.example.MiniProjectGroup5.exception.RecordNotFoundException;
import com.example.MiniProjectGroup5.model.Employee;
import com.example.MiniProjectGroup5.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepo;


    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public Page<Employee> findAllEmployees(Pageable pageable) {
        return employeeRepo.findAll(pageable);
    }

    @Override
    public Employee findEmployeeById(Long employeeId) throws RecordNotFoundException {
        Optional<Employee> employeeOptional = employeeRepo.findById(employeeId);
        if (employeeOptional.isPresent()) {
            return employeeOptional.get();
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public Employee updateEmployee(Long employeeId, Employee newEmployee) throws RecordNotFoundException {
        Optional<Employee> employeeOptional = employeeRepo.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setName(newEmployee.getName());
            employee.setLevel(newEmployee.getLevel());
            employee.setCommunity(newEmployee.getCommunity());
            employee.setEmail(newEmployee.getEmail());
            return employeeRepo.save(employee);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void deleteEmployee(Long employeeId) throws RecordNotFoundException {
        Optional<Employee> authorOptional = employeeRepo.findById(employeeId);
        if (authorOptional.isPresent()) {
            employeeRepo.delete(authorOptional.get());
        } else {
            throw new RecordNotFoundException();
        }

    }

    @Override
    public Page<Employee> findByType(CommunityType communityType, Pageable pageable) throws RecordNotFoundException {
        List<Employee> employeesWithSpecificType = employeeRepo.findAll(pageable)
                .stream()
                .filter(employee -> employee.getCommunity().equals(communityType)).toList();
        return new PageImpl<>(employeesWithSpecificType);
    }

}
