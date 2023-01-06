package com.example.demo.Model.Repositories;

import com.example.demo.Model.Entities.OnboardActivity;
import com.example.demo.Model.Entities.Port;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OnBoardActivityRepository extends JpaRepository<OnboardActivity, Long> {
    Optional<OnboardActivity> findByName(String name);

    Boolean existsByName(String name);
}
