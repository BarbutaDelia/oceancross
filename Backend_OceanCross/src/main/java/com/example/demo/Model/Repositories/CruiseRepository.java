package com.example.demo.Model.Repositories;

import com.example.demo.Model.Entities.Cruise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CruiseRepository extends JpaRepository<Cruise, Long> {
    Cruise findByName(String name);
}
