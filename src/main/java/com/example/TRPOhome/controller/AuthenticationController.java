package com.example.TRPOhome.controller;

import com.example.TRPOhome.dto.UserAuthenticationDto;
import com.example.TRPOhome.security2_0.JwtUtil;
import com.example.TRPOhome.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class AuthenticationController {


    private final AuthenticationManager authenticationManager;

    private final EmployeeService employeeService;

    private final JwtUtil jwtUtil;

    @PostMapping
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
}
