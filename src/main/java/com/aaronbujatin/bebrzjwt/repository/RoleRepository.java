package com.aaronbujatin.bebrzjwt.repository;

import com.aaronbujatin.bebrzjwt.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
