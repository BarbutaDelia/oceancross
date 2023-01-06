package com.example.demo.Model.Repositories;

import com.example.demo.Model.Entities.PortActivities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PortActivitiesRepository extends JpaRepository<PortActivities,Long>
{
    public List<Long> findIdByPortId(Long id);
    public List<PortActivities> findByPortId(Long id);
}