package com.example.demo.Model.Repositories;

import com.example.demo.Model.Entities.PortActivities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PortActivitiesRepository extends JpaRepository<PortActivities,Long>
{
    public List<Long> findIdByPortId(Long id);
    public List<PortActivities> findByPortId(Long id);
    public PortActivities findByName(String name);
    List<PortActivities> findByPortIdAndUserId(Long portId, Long userId);

    //@Query("Select p FROM port_activities WHERE p.id= : id")
    public PortActivities findPortById(Long id);
    void deleteByNameAndUserIdAndPortId(String name, Long userId, Long portId);

    void deleteAllByUserIdAndPortId(Long userId, Long portId);
}
