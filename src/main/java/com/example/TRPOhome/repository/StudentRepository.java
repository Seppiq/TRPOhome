package com.example.TRPOhome.repository;

import com.example.TRPOhome.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
    Student findByLastname(String lastname);
}
