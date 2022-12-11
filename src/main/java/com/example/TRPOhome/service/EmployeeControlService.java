package com.example.TRPOhome.service;

import com.example.TRPOhome.dto.EmployeeControllerCreationDto;
import com.example.TRPOhome.model.EmployeeControl;

import java.util.List;

public interface EmployeeControlService {
    List<EmployeeControl> findAll();

    void save(EmployeeControllerCreationDto employeeControl);

    void delete(EmployeeControl employeeControl);
}
