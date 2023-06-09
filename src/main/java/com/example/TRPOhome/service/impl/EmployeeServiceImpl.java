package com.example.TRPOhome.service.impl;

import com.example.TRPOhome.model.ConfirmationToken;
import com.example.TRPOhome.model.Employee;
import com.example.TRPOhome.repository.ConfirmationTokenRepository;
import com.example.TRPOhome.repository.EmployeeRepository;
import com.example.TRPOhome.security2_0.userDetails.JwtUser;
import com.example.TRPOhome.service.EmailService;
import com.example.TRPOhome.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    EmailService emailService;

    private final EmployeeRepository employeeRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            Employee employee = employeeRepository.findByGoogleNameIgnoreCase(token.getEmployeeEntity().getGoogleName());
            employeeRepository.save(employee);
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }

    @Override
    public ResponseEntity<?> saveUser(Employee employee) {
        if (employeeRepository.existsEmployeeByGoogleName(employee.getGoogleName())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        employee.setRole("ROLE_USER");
        employee.setPassword(encoder.encode(employee.getPassword()));
        employeeRepository.save(employee);

        ConfirmationToken confirmationToken = new ConfirmationToken(employee);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(employee.getGoogleName());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());

        return ResponseEntity.ok("Verify email by the link sent on your email address");
    }

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
