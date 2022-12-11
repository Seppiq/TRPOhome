package com.example.TRPOhome.repository;

import com.example.TRPOhome.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Employee findByLastname(String lastname);

    @Override
    Optional<Employee> findById(Long aLong);

    Optional<Employee> findByUsername(String string);
}
