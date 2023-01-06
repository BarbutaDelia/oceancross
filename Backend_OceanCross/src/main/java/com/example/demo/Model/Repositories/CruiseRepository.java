package com.example.demo.Model.Repositories;

import com.example.demo.Model.Entities.Cruise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CruiseRepository extends JpaRepository<Cruise, Long> {
//    Cruise findByName(String name);
    @Query("Select c from Cruise c Where c.id IN (:ids)")
    public List<Cruise> findCruisesByIds(@Param("ids") List<Long> ids);
}
