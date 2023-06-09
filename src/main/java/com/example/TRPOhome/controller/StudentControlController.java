package com.example.TRPOhome.controller;

import com.example.TRPOhome.dto.StudentControllerCreationDto;
import com.example.TRPOhome.model.StudentControl;
import com.example.TRPOhome.service.StudentControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student_control")
public class StudentControlController {

    private final StudentControlService studentControlService;

    @GetMapping()
    public ResponseEntity<List<StudentControl>> get() {
        return ResponseEntity.ok(studentControlService.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody StudentControllerCreationDto studentControllerCreationDto) {
        studentControlService.save(studentControllerCreationDto);
        return ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody StudentControl studentControl) {
        studentControlService.delete(studentControl);
        return ok().build();
    }
}
