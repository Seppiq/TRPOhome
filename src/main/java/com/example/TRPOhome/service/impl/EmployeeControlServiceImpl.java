package com.example.TRPOhome.service.impl;

import com.example.TRPOhome.dto.EmployeeControllerCreationDto;
import com.example.TRPOhome.model.EmployeeControl;
import com.example.TRPOhome.repository.EmployeeControlRepository;
import com.example.TRPOhome.repository.EmployeeRepository;
import com.example.TRPOhome.service.EmployeeControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeControlServiceImpl implements EmployeeControlService {

    private final EmployeeControlRepository employeeControlRepository;

    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeControl> findAll() {
        return (List<EmployeeControl>) employeeControlRepository.findAll();
    }

    @Override
    public void save(EmployeeControllerCreationDto employeeControllerCreationDto) {
        EmployeeControl employeeControl = EmployeeControl.builder()
                .id(employeeControllerCreationDto.getId())
                .at(employeeControllerCreationDto.getAt())
                .dt(employeeControllerCreationDto.getDt())
                .employee(employeeRepository.findById(employeeControllerCreationDto.getEmployeeId()).get())
                .build();
        employeeControlRepository.save(employeeControl);
    }

    @Override
    public void delete(EmployeeControl employeeControl) {
        employeeControlRepository.delete(employeeControl);
    }
}

