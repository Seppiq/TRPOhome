package com.example.TRPOhome.service.impl;

import com.example.TRPOhome.dto.StudentControllerCreationDto;
import com.example.TRPOhome.model.StudentControl;
import com.example.TRPOhome.repository.StudentControlRepository;
import com.example.TRPOhome.repository.StudentRepository;
import com.example.TRPOhome.service.StudentControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentControlServiceImpl implements StudentControlService {

    private final StudentControlRepository studentControlRepository;

    private final StudentRepository studentRepository;

    @Override
    public List<StudentControl> findAll() {
        return (List<StudentControl>) studentControlRepository.findAll();
    }

    @Override
    public void save(StudentControllerCreationDto studentControllerCreationDto) {
        StudentControl studentControl = StudentControl.builder()
                .id(studentControllerCreationDto.getId())
                .at(studentControllerCreationDto.getAt())
                .dt(studentControllerCreationDto.getDt())
                .student(studentRepository.findById(studentControllerCreationDto.getStudentId()).get())
                .build();
        studentControlRepository.save(studentControl);
    }

    @Override
    public void delete(StudentControl studentControl) {
        studentControlRepository.delete(studentControl);
    }
}
