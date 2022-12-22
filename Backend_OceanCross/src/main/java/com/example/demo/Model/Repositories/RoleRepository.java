package com.example.demo.Model.Repositories;

import java.util.Optional;

import com.example.demo.Model.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
