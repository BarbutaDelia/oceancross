package com.example.demo.Model.Repositories;

import com.example.demo.Model.Entities.Port;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortRepository extends JpaRepository<Port, Long> {
    Port findByName(String name);
//    Port findById(Long id);

    Boolean existsByName(String name);
}
