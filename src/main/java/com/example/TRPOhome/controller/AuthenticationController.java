package com.example.TRPOhome.controller;

import com.example.TRPOhome.dto.UserAuthenticationDto;
import com.example.TRPOhome.model.Employee;
import com.example.TRPOhome.repository.RoleRepo;
import com.example.TRPOhome.request.MessageResponse;
import com.example.TRPOhome.request.SignupRequest;
import com.example.TRPOhome.security2_0.JwtUtil;
import com.example.TRPOhome.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;


@RestController
@RequiredArgsConstructor
@RequestMapping
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final EmployeeService employeeService;

    private final JwtUtil jwtUtil;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepo roleRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody UserAuthenticationDto userAuthenticationDto) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userAuthenticationDto.getUserName(),
                            userAuthenticationDto.getPassword()
                    )
            );
        } catch (BadCredentialsException badCredentialsException) {
            throw new UsernameNotFoundException("Incorrect email or password", badCredentialsException);
        }

        final UserDetails userDetails = employeeService.loadUserByUsername(userAuthenticationDto.getUserName());

        String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        Employee employee = new Employee(signUpRequest.getName(), signUpRequest.getLastname(), signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()), "ROLE_USER", LocalDate.now(), "1234567890", "Manager");
        employee.setRole("ROLE_USER");
        employeeService.save(employee);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Employee user) {
        return employeeService.saveUser(user);
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken) {
        return employeeService.confirmEmail(confirmationToken);
    }
}
