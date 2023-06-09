package com.example.TRPOhome.controller;

import com.example.TRPOhome.model.Student;
import com.example.TRPOhome.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
@CrossOrigin("*")
public class StudentController {
    private final StudentService studentService;

    @GetMapping()
    public ResponseEntity<List<Student>> get() {
        System.out.println(studentService.findAll());
        return ok(studentService.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> save(@Validated @RequestBody Student student) {
        studentService.save(student);
        return ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody Student student) {
        studentService.delete(student);
        return ok().build();
    }

    @GetMapping("/{lastname}")
    public ResponseEntity<Student> findByLastname(@PathVariable String lastname) {
        return ok(studentService.findByLastname(lastname));
    }
}
