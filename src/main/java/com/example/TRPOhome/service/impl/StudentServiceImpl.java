package com.example.TRPOhome.service.impl;

import com.example.TRPOhome.model.Student;
import com.example.TRPOhome.repository.StudentRepository;
import com.example.TRPOhome.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void delete(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public Student findByLastname(String lastname) {
        return studentRepository.findByLastname(lastname);
    }
}
