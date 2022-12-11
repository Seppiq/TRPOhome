package com.example.TRPOhome.controller;

import com.example.TRPOhome.exception.ResourceNotFoundException;
import com.example.TRPOhome.export.ExcelService;
import com.example.TRPOhome.export.ResourceDTO;
import com.example.TRPOhome.model.Employee;
import com.example.TRPOhome.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;

    private final ExcelService excelService;

    @GetMapping()
    public ResponseEntity<List<Employee>> get() {
        return ok(employeeService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Void> save(@Validated @RequestBody Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        //employee.setRole("ROLE_USER");
        employeeService.save(employee);
        return ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employee) {
        Employee updateEmployee = employeeService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        updateEmployee.setName(employee.getName());
        updateEmployee.setLastname(employee.getLastname());
        updateEmployee.setUsername(employee.getUsername());

        employeeService.save(updateEmployee);

        return ResponseEntity.ok(updateEmployee);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id) {
        Employee employee = employeeService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        employeeService.delete(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{lastname}")
    public ResponseEntity<Employee> findByLastname(@PathVariable String lastname) {
        return ok(employeeService.findByLastname(lastname));
    }

    //@GetMapping("/export/xls")
    @GetMapping("/export/xls")
    public ResponseEntity<Resource> exportUsers() {
        ResourceDTO resourceDTO = excelService.exportUsers();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition",
                "attachment; filename=" + "User.xlsx");

        return ResponseEntity.ok()
                .contentType(resourceDTO.getMediaType())
                .headers(httpHeaders)
                .body(resourceDTO.getResource());
    }
}
