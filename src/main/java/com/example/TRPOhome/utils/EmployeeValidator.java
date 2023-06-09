package com.example.TRPOhome.utils;

import com.example.TRPOhome.model.Employee;
import com.example.TRPOhome.service.impl.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class EmployeeValidator implements Validator {

    private final EmployeeServiceImpl employeeService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Employee employee = (Employee) target;

        employeeService.loadUserByUsername(employee.getUsername());
    }
}
