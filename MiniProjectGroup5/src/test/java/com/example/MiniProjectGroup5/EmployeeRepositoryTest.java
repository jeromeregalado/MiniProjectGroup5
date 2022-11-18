package com.example.MiniProjectGroup5;

import com.example.MiniProjectGroup5.model.Employee;
import com.example.MiniProjectGroup5.repository.EmployeeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    @DisplayName("GIVEN employees from SQL " +
            "WHEN findAll() is executed " +
            "THEN result should validate that repository is not empty")
    public void testRepositoryIsNotEmpty() {
        // Arrange
        // Act
        Page<Employee> employeeList = employeeRepository.findAll(PageRequest.of(0,20));
        // Assert
        assertThat(employeeList).isNotEmpty();
    }

//    @Test
//    @DisplayName("GIVEN employees from SQL " +
//            "WHEN FindEmployeeByName() is executed with input of name " +
//            "THEN result should return employees whose name is the same")
//
//    public void testFindEmployeeByName() {
//        // Arrange
//        List<Employee> employeeList = employeeRepository.findAll();
//        Employee jojo = employeeList.get(0);
//        Employee eshant = employeeList.get(1);
//        Employee jerome = employeeList.get(2);
//        String name = "Jojo";
//
//        // Act
//        List<Employee> actualList = employeeRepository.findEmployeesByName(name);
//
//        // Assert
//        assertThat(actualList).containsExactly(jojo);
//    }
//
//    @Test
//    @DisplayName("GIVEN employees from SQL " +
//            "WHEN FindEmployeeByLevel() is executed with input of name " +
//            "THEN result should return employees whose level is the same")
//    public void testFindEmployeesByLevel() {
//        // Arrange
//        List<Employee> employeeList = employeeRepository.findAll();
//        Employee jojo = employeeList.get(0);
//        Employee eshant = employeeList.get(1);
//        Employee jerome = employeeList.get(2);
//        String level = "Dev";
//
//        // Act
//        List<Employee> actualList = employeeRepository.findEmployeesByLevel(level);
//
//        // Assert
//        assertThat(actualList).containsExactly(jojo,eshant,jerome);
//    }
//
//    @Test
//    @DisplayName("GIVEN employees from SQL " +
//            "WHEN FindEmployeeByEmail() is executed with input of name " +
//            "THEN result should return employees whose email is the same")
//    public void testFindEmployeesByEmail() {
//        // Arrange
//        List<Employee> employeeList = employeeRepository.findAll();
//        Employee jojo = employeeList.get(0);
//        Employee eshant = employeeList.get(1);
//        Employee jerome = employeeList.get(2);
//        String email = "jojo@jojo.com";
//
//        // Act
//        List<Employee> actualList = employeeRepository.findEmployeesByEmail(email);
//
//        // Assert
//        assertThat(actualList).containsExactly(jojo);
//    }
//
//    @Test
//    @DisplayName("GIVEN employees from SQL " +
//            "WHEN FindEmployeeByCommunity() is executed with input of name " +
//            "THEN result should return employees whose community is the same")
//    public void testFindEmployeesByCommunity() {
//        // Arrange
//        List<Employee> employeeList = employeeRepository.findAll();
//        Employee jojo = employeeList.get(0);
//        Employee eshant = employeeList.get(1);
//        Employee jerome = employeeList.get(2);
//        String community = "Java";
//
//        // Act
//        List<Employee> actualList = employeeRepository.findEmployeesByCommunity(community);
//
//        // Assert
//        assertThat(actualList).containsExactly(jojo,eshant,jerome);
//    }

}
