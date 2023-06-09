package com.example.TRPOhome.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class EmployeeControllerCreationDto {

    private Long id;

    private Date at;

    private Date dt;

    private Long employeeId;
}
