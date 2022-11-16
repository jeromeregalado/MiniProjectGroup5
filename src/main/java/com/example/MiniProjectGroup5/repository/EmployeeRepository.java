package com.example.MiniProjectGroup5.repository;

import com.example.MiniProjectGroup5.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
    List<Employee> findEmployeesByName(String name);
    List<Employee> findEmployeesByLevel(String level);
    List<Employee> findEmployeesByEmail(String email);
    List<Employee> findEmployeesByCommunity(String community);

}
