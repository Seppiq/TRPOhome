package com.example.TRPOhome.controller;

import com.example.TRPOhome.controller.EmployeeController;
import com.example.TRPOhome.model.Employee;
import com.example.TRPOhome.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.modelmapper.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired MockMvc mvc;
    @MockBean EmployeeService employeeService;

    @Test
    public void getEmployeesTest() throws Exception {

        List<Employee> empList = new ArrayList<>();
        //empList.add(new Employee("ann24", "sadf231sa", "Познякова", "Полина"));
        //empList.add(new Employee("Полина"));

        Mockito.when(employeeService.findAll()).thenReturn(empList);

        mvc.perform(get("/api/employee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect((ResultMatcher) jsonPath("$[0].name", is("John")))
                .andExpect((ResultMatcher) jsonPath("$[1].name", is("Jane")));

        Mockito.verify(employeeService, Mockito.times(1)).findAll();
    }
}




