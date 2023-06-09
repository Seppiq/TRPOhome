package com.example.TRPOhome.service;

import com.example.TRPOhome.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface EmployeeService extends UserDetailsService {

    ResponseEntity<?> confirmEmail(String confirmationToken);

    ResponseEntity<?> saveUser(Employee employee);

    List<Employee> findAll();

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void save(Employee employee);

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Employee employee);

    Optional<Employee> findById(Long aLong);

    Employee findByLastname(String lastname);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
