package com.example.MiniProjectGroup5;

import com.example.MiniProjectGroup5.enums.CommunityType;
import com.example.MiniProjectGroup5.model.Employee;
import com.example.MiniProjectGroup5.repository.EmployeeRepository;
import com.example.MiniProjectGroup5.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

    private Employee jojo, eshant, jerome;
    private List<Employee> employeeList;


    @BeforeEach
    public void setup() {
        jojo = new Employee("Jojo","Dev","jojo@jojo.com", CommunityType.JAVA);
        eshant = new Employee("Eshant","Dev","eshant.manghnani@softvision.com",CommunityType.WEB);
        jerome = new Employee("Jerome","Dev","jeromeeregaladoo@gmail.com",CommunityType.QE);
        employeeList = List.of(jojo,eshant,jerome);
    }

    @Test
    @DisplayName("GIVEN employees from SQL " +
            "WHEN FindFindEmployeeByName() is executed " +
            "THEN result should validate result is name specified")
    public void TestFindEmployeeByName()
    {
        setup();
        //arrange
        String name = "Jojo";
        Mockito.when(employeeRepository.findEmployeesByName(name))
                .thenReturn(employeeList.stream().filter(employee -> employee.getName().equals(name)).toList());

        //act
        List<Employee> filteredEmployee = employeeRepository.findEmployeesByName(name);

        //assert
        assertThat(filteredEmployee).containsExactly(jojo);
    }


    @Test
    @DisplayName("GIVEN employees from SQL " +
            "WHEN FindFindEmployeeByName() is executed " +
            "THEN result should validate result is name specified")
    public void TestFindAllEmployees()
    {
        setup();
        Pageable pageable = PageRequest.of(0, 20);
        Page<Employee> employees = new PageImpl<>(employeeList, pageable, employeeList.size());
        //arrange
        Mockito.when(employeeServiceImpl.findAllEmployees(pageable))
                .thenReturn(employees);

        //act
        Page<Employee> filteredEmployee = employeeServiceImpl.findAllEmployees(pageable);

        //assert
        assertEquals(filteredEmployee,employees);
    }

    @Test
    @DisplayName("GIVEN employees from SQL " +
            "WHEN findEmployeesByCommunity() is executed " +
            "THEN result should validate result is name specified")
    public void TestFindEmployeeByType()
    {
        setup();
        //arrange
        Mockito.when(employeeRepository.findEmployeesByCommunity(CommunityType.JAVA))
                .thenReturn(employeeList.stream().filter(employee -> employee.getCommunity().equals(CommunityType.JAVA)).toList());

        //act
        List<Employee> filteredEmployee = employeeRepository.findEmployeesByCommunity(CommunityType.JAVA);

        //assert
        assertThat(filteredEmployee).containsExactly(jojo);
    }
}
