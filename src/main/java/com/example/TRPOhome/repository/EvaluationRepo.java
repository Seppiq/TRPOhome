package com.example.TRPOhome.repository;

import com.example.TRPOhome.model.EmployeeControl;
import com.example.TRPOhome.model.EvaluationOfTheRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationRepo extends CrudRepository<EvaluationOfTheRoom, Long> {
}
