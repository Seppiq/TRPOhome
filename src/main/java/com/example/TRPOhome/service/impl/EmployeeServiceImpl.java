package com.example.TRPOhome.service.impl;

import com.example.TRPOhome.model.Employee;
import com.example.TRPOhome.repository.EmployeeRepository;
import com.example.TRPOhome.security2_0.userDetails.JwtUser;
import com.example.TRPOhome.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public void save(Employee employee) {
        employee.setRole("ROLE_USER");
        employeeRepository.save(employee);
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public Optional<Employee> findById(Long aLong) {
        return employeeRepository.findById(aLong);
    }

    @Override
    public Employee findByLastname(String lastname) {
        return employeeRepository.findByLastname(lastname);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee = employeeRepository.findByUsername(username);
        if (employee.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new JwtUser(employee.get());
    }

}
