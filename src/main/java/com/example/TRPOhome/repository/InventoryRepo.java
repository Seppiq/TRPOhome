package com.example.TRPOhome.repository;

import com.example.TRPOhome.model.EmployeeControl;
import com.example.TRPOhome.model.Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends CrudRepository<Inventory, Long> {
}
