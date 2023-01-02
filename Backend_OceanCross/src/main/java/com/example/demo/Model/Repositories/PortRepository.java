package com.example.demo.Model.Repositories;

import com.example.demo.Model.Entities.Port;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PortRepository extends JpaRepository<Port, Long> {
    Port findByName(String name);
}
