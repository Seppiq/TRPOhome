package com.example.TRPOhome.service;

import com.example.TRPOhome.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();

    void save(Student student);

    void delete(Student student);

    Student findByLastname(String lastname);
}
