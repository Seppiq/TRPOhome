package com.example.TRPOhome.service;

import com.example.TRPOhome.model.Employee;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface EmployeeService extends UserDetailsService {

    List<Employee> findAll();

    void save(Employee employee);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Employee employee);

    Optional<Employee> findById(Long aLong);

    Employee findByLastname(String lastname);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
