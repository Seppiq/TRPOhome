//package com.example.TRPOhome.security;
//
//import com.example.TRPOhome.model.Employee;
//import com.example.TRPOhome.service.EmployeeService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import static org.springframework.http.ResponseEntity.ok;
//
//@CrossOrigin("*")
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/auth")
//public class AuthController {
//
//    private final EmployeeService employeeService;
//
//    @PostMapping()
//    public ResponseEntity<Void> save(@Validated @RequestBody Employee employee) {
//        employeeService.loadUserByUsername(employee.getUsername());
//        return ok().build();
//    }
//}
