package com.example.TRPOhome.repository;

import com.example.TRPOhome.model.enums.Role;
import com.example.TRPOhome.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRole name);
}