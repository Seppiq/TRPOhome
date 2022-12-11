package com.example.TRPOhome.repository;

import com.example.TRPOhome.model.StudentControl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentControlRepository extends CrudRepository<StudentControl, Long> {

}
