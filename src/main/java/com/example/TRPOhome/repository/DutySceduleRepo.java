package com.example.TRPOhome.repository;

import com.example.TRPOhome.model.DutySchedule;
import com.example.TRPOhome.model.EmployeeControl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DutySceduleRepo extends CrudRepository<DutySchedule, Long> {
}
