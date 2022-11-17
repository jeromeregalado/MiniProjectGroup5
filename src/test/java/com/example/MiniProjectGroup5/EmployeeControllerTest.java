package com.example.MiniProjectGroup5;

import com.example.MiniProjectGroup5.enums.CommunityType;
import com.example.MiniProjectGroup5.model.Employee;
import com.example.MiniProjectGroup5.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    private Employee Jojo = new Employee("Jojo", "Dev", "jojo@jojo.com", CommunityType.JAVA);
    private Employee Eshant = new Employee("Eshant", "Dev", "eshant.manghnani@softvision.com", CommunityType.WEB);
    private Employee Jerome = new Employee("Jerome", "Dev", "jeromeeregaladoo@gmail.com", CommunityType.QE);
    List<Employee> employeeLists = List.of(Jojo, Eshant, Jerome);
    private Page<Employee> employees;


    @Test
    @DisplayName("GIVEN a get request " +
            "WHEN findAllEmployees" +
            "THEN result should return Employees Jojo, Eshant, Jerome")

    public void testFindAllEmployees() throws Exception {


        Pageable pageable1 = PageRequest.of(0, 20);
        employees = new PageImpl<>(employeeLists, pageable1, employeeLists.size());
        when(employeeService.findAllEmployees(pageable1))
                .thenReturn(employees);
        ObjectMapper mapper = new ObjectMapper();
        String expectedJSONString = mapper.writeValueAsString(employees);
        mockMvc.perform(MockMvcRequestBuilders.get("/employees", employees)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedJSONString));


    }

    @Test
    @DisplayName("" +
            "GIVEN a get request for ID" +
            "WHEN findEmployeeById()" +
            "THEN response should be specific Id")
    public void getEmployeeById() throws Exception {

        long id = 1;
        Pageable pageable2 = PageRequest.of(0, 20);
        when(employeeService.findEmployeeById(id))
                .thenReturn(Jojo);
        ObjectMapper mapper = new ObjectMapper();
        String expectedJSONString = mapper.writeValueAsString(Jojo);
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{employeeId}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedJSONString));
    }


}
