package com.example.TRPOhome.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class StudentControllerCreationDto {

    private Long id;

    private Date at;

    private Date dt;

    private Long studentId;
}
