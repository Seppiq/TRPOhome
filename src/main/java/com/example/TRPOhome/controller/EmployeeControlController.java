package com.example.TRPOhome.controller;

import com.example.TRPOhome.dto.EmployeeControllerCreationDto;
import com.example.TRPOhome.model.EmployeeControl;
import com.example.TRPOhome.service.EmployeeControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee_control")
public class EmployeeControlController {
    private final EmployeeControlService employeeControlService;

    @GetMapping()
    public ResponseEntity<List<EmployeeControl>> get() {
        return ResponseEntity.ok(employeeControlService.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody EmployeeControllerCreationDto employeeControl) {
        employeeControlService.save(employeeControl);
        return ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody EmployeeControl employeeControl) {
        employeeControlService.delete(employeeControl);
        return ok().build();
    }
}
