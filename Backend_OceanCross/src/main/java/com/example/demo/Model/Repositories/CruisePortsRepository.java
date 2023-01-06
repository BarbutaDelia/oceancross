package com.example.demo.Model.Repositories;

import com.example.demo.Model.Entities.CruisePort;
import com.example.demo.Model.Entities.OnboardActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CruisePortsRepository extends JpaRepository<CruisePort, Long> {
}
