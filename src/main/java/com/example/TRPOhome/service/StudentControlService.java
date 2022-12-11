package com.example.TRPOhome.service;

import com.example.TRPOhome.dto.StudentControllerCreationDto;
import com.example.TRPOhome.model.StudentControl;

import java.util.List;

public interface StudentControlService {
    List<StudentControl> findAll();

    void save(StudentControllerCreationDto studentControllerCreationDto);

    void delete(StudentControl studentControl);
}
